package com.github.szymonrudnicki.songapp.data.repositories.songs

import com.github.szymonrudnicki.songapp.data.json.SongsLocalSource
import com.github.szymonrudnicki.songapp.data.mapper.ModelMapper
import com.github.szymonrudnicki.songapp.data.rest.SongsRemoteSource
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class SongsRepositoryImpl(
    remoteSource: SongsRemoteSource,
    localSource: SongsLocalSource
) : SongsRepository {

    private val localSingle =
            localSource.getSongs()
                    .map(ModelMapper::mapSongsFromJSONToDomain)
                    .filterOutModelsWithEmptyValues()

    private val remoteSingle =
            remoteSource.searchForSongs("trains")
                    .map(ModelMapper::mapSongsFromResponseToDomain)
                    .filterOutModelsWithEmptyValues()

    override fun getSongsFromLocal(): Single<List<SongDomainModel>> = localSingle
    override fun getSongsFromRemote(): Single<List<SongDomainModel>> = remoteSingle

    override fun getSongsFromLocalAndRemote(): Single<List<SongDomainModel>> =
            Single.zip(localSingle.onErrorReturn { listOf() }, remoteSingle.onErrorReturn { listOf() },
                    BiFunction { localResult, remoteResult ->
                        mutableListOf<SongDomainModel>().apply {
                            addAll(localResult)
                            addAll(remoteResult)
                        }
                    }
            )

    private fun Single<List<SongDomainModel>>.filterOutModelsWithEmptyValues() =
            this.map {
                it.filter { song ->
                    song.title.isNotEmpty() and song.artist.isNotEmpty() and song.releaseYear.isNotEmpty()
                }
            }
}