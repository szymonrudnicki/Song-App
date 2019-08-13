package com.github.szymonrudnicki.songapp.data.rest

import com.github.szymonrudnicki.songapp.data.model.SongsResponseModel
import io.reactivex.Single

class SongsRemoteSource(private val iTunesApiService: ITunesApiService) {

    fun searchForSongs(searchTerm: String): Single<SongsResponseModel> =
            iTunesApiService.searchFor(searchTerm)
}