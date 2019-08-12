package com.github.szymonrudnicki.songapp.domain.songs.repositories

import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import io.reactivex.Single

interface SongsRepository {
    fun getSongsFromLocal(): Single<List<SongDomainModel>>
    fun getSongsFromRemote(): Single<List<SongDomainModel>>
    fun getSongsFromLocalAndRemote(): Single<List<SongDomainModel>>
}