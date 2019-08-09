package com.github.szymonrudnicki.songapp.data.rest

import io.reactivex.Single

class SongsRemoteSource(private val iTunesApiService: ITunesApiService) {

    fun searchForSongs(searchTerm: String): Single<List<SongResponseModel>> =
            iTunesApiService.searchFor(searchTerm)
}