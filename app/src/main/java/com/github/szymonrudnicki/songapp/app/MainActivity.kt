package com.github.szymonrudnicki.songapp.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.github.szymonrudnicki.songapp.R
import com.github.szymonrudnicki.songapp.app.common.extensions.observe
import com.github.szymonrudnicki.songapp.app.common.extensions.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setStatusBarColor()

        observe(mainViewModel.mainLiveData, ::updateUIState)
    }

    private fun updateUIState(event: MainUIEvent?) {
        when (event) {
            is MainUIEvent.SongsChanged -> Toast.makeText(this, event.songs.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            if (item.itemId == R.id.action_select_source) {
                mainViewModel.getSongsFromLocal()
                // TODO: show selection dialog
                true
            } else super.onOptionsItemSelected(item)

    private fun setStatusBarColor() = with(window) {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = ContextCompat.getColor(context, R.color.background_status_bar)
    }
}
