package com.github.szymonrudnicki.songapp.data.rest.response

import com.google.gson.annotations.SerializedName

data class SongsResponseModel(
    @SerializedName("resultCount")
    val songsCount: Int,
    @SerializedName("results")
    val songs: List<SongResponseModel>
)