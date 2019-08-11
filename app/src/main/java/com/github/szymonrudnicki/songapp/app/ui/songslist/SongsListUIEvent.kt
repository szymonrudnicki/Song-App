package com.github.szymonrudnicki.songapp.app.ui.songslist

import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel

sealed class SongsListUIEvent {
    data class SongsChanged(val songs: List<SongDomainModel>) : SongsListUIEvent()
    data class Failed(val throwable: Throwable) : SongsListUIEvent()
}