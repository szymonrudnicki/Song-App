package com.github.szymonrudnicki.songapp.domain.songs.usecases

import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel

sealed class GetSongsResult {
    data class Success(val songs: List<SongDomainModel>) : GetSongsResult()
    object Failed : GetSongsResult()
}