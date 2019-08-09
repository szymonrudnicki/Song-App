package com.github.szymonrudnicki.songapp.data.repositories

import com.github.szymonrudnicki.songapp.data.json.SongsLocalSource
import com.github.szymonrudnicki.songapp.data.mapper.ModelMapper
import com.github.szymonrudnicki.songapp.data.rest.SongsRemoteSource
import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsResult
import io.reactivex.Single

class SongsRepositoryImpl(
        private val remoteSource: SongsRemoteSource,
        private val localSource: SongsLocalSource
) : SongsRepository {

    override fun getSongsFromLocal(): Single<GetSongsResult> =
            localSource.getSongs()
                    .map(ModelMapper::mapSongsFromJSONToDomain)
                    .map {
                        GetSongsResult.Success(it)
                    }

    override fun getSongsFromRemote(): Single<GetSongsResult> =
            remoteSource.searchForSongs("trains")
                    .map(ModelMapper::mapSongsFromResponseToDomain)
                    .map {
                        GetSongsResult.Success(it)
                    }

    override fun getSongsFromLocalAndRemote(): Single<GetSongsResult> =
            Single.just(GetSongsResult.Failed)
            //getSongsFromRemote().concatWith(getSongsFromLocal())
}