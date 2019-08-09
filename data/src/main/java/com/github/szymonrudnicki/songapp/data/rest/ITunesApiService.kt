package com.github.szymonrudnicki.songapp.data.rest

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ITunesApiService {

    @GET("/search")
    fun searchFor(@Path("term") searchTerm: String) : Single<List<SongResponseModel>>

}