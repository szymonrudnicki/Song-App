package com.github.szymonrudnicki.songapp.data.rest

import com.github.szymonrudnicki.songapp.data.model.SongsResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApiService {
    // due to lack of requirements, I used example endpoint
    @GET("/search")
    fun searchFor(@Query("term") searchTerm: String): Single<SongsResponseModel>
}