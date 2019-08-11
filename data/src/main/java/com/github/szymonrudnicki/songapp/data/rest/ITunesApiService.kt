package com.github.szymonrudnicki.songapp.data.rest

import com.github.szymonrudnicki.songapp.data.rest.response.SongsResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApiService {

    @GET("/search")
    fun searchFor(@Query("term") searchTerm: String): Single<SongsResponseModel>

}