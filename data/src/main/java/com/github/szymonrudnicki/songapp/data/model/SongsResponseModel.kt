package com.github.szymonrudnicki.songapp.data.model

import com.google.gson.annotations.SerializedName

data class SongsResponseModel(
    @SerializedName("resultCount")
    val songsCount: Int,
    @SerializedName("results")
    val songs: List<SongResponseModel>
)