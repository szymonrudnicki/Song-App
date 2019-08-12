package com.github.szymonrudnicki.songapp.app.ui.songslist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.szymonrudnicki.songapp.R
import com.github.szymonrudnicki.songapp.app.common.extensions.gone
import com.github.szymonrudnicki.songapp.app.common.extensions.observe
import com.github.szymonrudnicki.songapp.app.common.extensions.viewModel
import com.github.szymonrudnicki.songapp.app.common.extensions.visible
import com.github.szymonrudnicki.songapp.app.ui.songslist.recycler.SongRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_songs_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import java.util.*

class SongsListFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val songsListViewModel: SongsListViewModel by viewModel()
    private val songsAdapter = SongRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_songs_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(songsListViewModel.eventLiveData, ::updateUIState)
        observe(songsListViewModel.loadingLiveData, ::toggleLoadingScreen)
        songsListViewModel.getSongsFromLastSource()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_songs_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            if (item.itemId == R.id.action_select_source) {
                showSourceSelectionDialog()
                true
            } else super.onOptionsItemSelected(item)

    private fun updateUIState(event: SongsListUIEvent?) {
        when (event) {
            is SongsListUIEvent.SongsChanged -> songsAdapter.songsList = event.songs
            is SongsListUIEvent.Failed -> Toast.makeText(context, "FAIL! ${event.throwable}", Toast.LENGTH_LONG).show()
        }
    }

    private fun toggleLoadingScreen(isLoading: Boolean?) {
        if(isLoading!!) {
            loading_screen.visible()
        } else {
            loading_screen.gone()
        }
    }

    private fun setupRecyclerView() {
        val itemSpaceDivider = DividerItemDecoration(context, LinearLayout.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(context!!, R.drawable.item_space_divider_1dp)!!)
        }
        songs_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = songsAdapter
            addItemDecoration(itemSpaceDivider)
        }
    }

    private fun showSourceSelectionDialog() {
        val sources = SourceType.values()
        val sourceTags = ArrayList(sources.map { it.tag }).toTypedArray()

        val sourceIndex = sourceTags.indexOfFirst { tag ->
            tag == songsListViewModel.getLastCheckedSourceTag()
        }

        var chosenSource = ""
        AlertDialog.Builder(context!!)
                .setTitle(R.string.select_source)
                .setSingleChoiceItems(sourceTags, sourceIndex) { _, index ->
                    chosenSource = sourceTags[index]
                }
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    songsListViewModel.handleSourceChoice(SourceType.getByTag(chosenSource))
                }
                .show()
    }
}