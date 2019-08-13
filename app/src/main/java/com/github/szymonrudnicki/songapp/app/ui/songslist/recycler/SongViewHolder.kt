package com.github.szymonrudnicki.songapp.app.ui.songslist.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import kotlinx.android.synthetic.main.item_song.view.*

class SongViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
    fun bind(song: SongDomainModel) = with(itemView) {
        item_song_title_text_view.text = song.title
        item_song_artist_text_view.text = song.artist
        item_song_release_year_text_view.text = song.releaseYear
    }
}
