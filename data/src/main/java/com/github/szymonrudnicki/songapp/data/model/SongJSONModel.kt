package com.github.szymonrudnicki.songapp.data.model

import com.google.gson.annotations.SerializedName

data class SongJSONModel(
    @SerializedName("Song Clean") val title: String,
    @SerializedName("ARTIST CLEAN") val artist: String,
    @SerializedName("Release Year") val releaseYear: String,
    @SerializedName("COMBINED") val combinedTitleWithArtist: String,
    @SerializedName("First?") val first: Int,
    @SerializedName("Year?") val year: Int,
    @SerializedName("PlayCount") val playCount: Int,
    @SerializedName("F*G") val fg: Int
)
