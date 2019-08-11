package com.github.szymonrudnicki.songapp.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.szymonrudnicki.songapp.R
import com.github.szymonrudnicki.songapp.app.common.extensions.observe
import com.github.szymonrudnicki.songapp.app.common.extensions.viewModel
import com.github.szymonrudnicki.songapp.app.ui.songslist.SongsListUIEvent
import com.github.szymonrudnicki.songapp.app.ui.songslist.SongsListViewModel
import com.github.szymonrudnicki.songapp.app.ui.songslist.recycler.SongRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val mainViewModel: SongsListViewModel by viewModel()
    private val songsAdapter = SongRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setStatusBarColor()

        val itemSpaceDivider = DividerItemDecoration(this, LinearLayout.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.item_space_divider_1dp)!!)
        }

        songs_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = songsAdapter
            addItemDecoration(itemSpaceDivider)
        }

        observe(mainViewModel.mainLiveData, ::updateUIState)
    }

    private fun updateUIState(event: SongsListUIEvent?) {
        when (event) {
            is SongsListUIEvent.SongsChanged -> songsAdapter.songsList = event.songs
            is SongsListUIEvent.Failed -> Toast.makeText(this, "FAIL! ${event.throwable}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            if (item.itemId == R.id.action_select_source) {
                mainViewModel.getSongsFromRemote()
                // TODO: show selection dialog
                true
            } else super.onOptionsItemSelected(item)

    private fun setStatusBarColor() = with(window) {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = ContextCompat.getColor(context, R.color.background_status_bar)
    }
}
