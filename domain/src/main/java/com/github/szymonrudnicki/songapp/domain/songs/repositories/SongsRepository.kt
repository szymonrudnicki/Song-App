package com.github.szymonrudnicki.songapp.domain.songs.repositories

import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsResult
import io.reactivex.Single

interface SongsRepository {
    fun getSongsFromLocal() : Single<GetSongsResult>
    fun getSongsFromRemote() : Single<GetSongsResult>
    fun getSongsFromLocalAndRemote() : Single<GetSongsResult>
}