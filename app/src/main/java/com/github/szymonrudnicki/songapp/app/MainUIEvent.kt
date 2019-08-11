package com.github.szymonrudnicki.songapp.app

import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel

sealed class MainUIEvent {
    data class SongsChanged(val songs: List<SongDomainModel>) : MainUIEvent()
    data class Failed(val throwable: Throwable) : MainUIEvent()
}