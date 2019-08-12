package com.github.szymonrudnicki.songapp.app.ui.songslist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.szymonrudnicki.songapp.R
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import kotlin.properties.Delegates

class SongRecyclerAdapter : RecyclerView.Adapter<SongViewHolder>() {

    var songsList: List<SongDomainModel> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SongViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_song, parent, false)
            )

    override fun getItemCount(): Int = songsList.size
}