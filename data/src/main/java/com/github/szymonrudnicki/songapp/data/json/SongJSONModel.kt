package com.github.szymonrudnicki.songapp.data.json

import com.google.gson.annotations.SerializedName

data class SongJSONModel(
        @SerializedName("Song Clean") val title: String,
        @SerializedName("ARTIST CLEAN") val artist: String,
        @SerializedName("Release Year") val releaseYear: Int,
        @SerializedName("COMBINED") val combinedTitleWithArtist: String,
        @SerializedName("First?") val isFirst: Boolean,
        @SerializedName("Year?") val isYear: Boolean,
        @SerializedName("PlayCount") val playCount: Int,
        @SerializedName("F*G") val fg: Int
)
