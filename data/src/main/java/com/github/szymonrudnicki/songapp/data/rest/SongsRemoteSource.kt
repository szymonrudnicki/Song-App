package com.github.szymonrudnicki.songapp.data.rest

import io.reactivex.Single
import retrofit2.http.Path

class SongsRemoteSource(
        private val iTunesApiService: ITunesApiService
) {

    fun searchForSongs(@Path("term") searchTerm: String): Single<List<SongResponseModel>> =
            iTunesApiService.searchFor(searchTerm)

}