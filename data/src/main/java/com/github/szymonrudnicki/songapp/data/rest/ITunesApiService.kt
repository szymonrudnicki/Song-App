package com.github.szymonrudnicki.songapp.data.rest

import io.reactivex.Single
import retrofit2.http.GET

interface ITunesApiService {

    @GET("search?term=jack+johnson")
    fun searchFor(searchTerm: String) : Single<List<SongResponseModel>>

}