package com.github.szymonrudnicki.songapp.data.json

import com.github.szymonrudnicki.songapp.data.common.Constants.JSON_PATH
import com.github.szymonrudnicki.songapp.data.extensions.fromJson
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import io.reactivex.Single
import java.io.FileReader

class SongsLocalSource(
        private val gson: Gson
) {

    fun getSongs(): Single<List<SongJSONModel>> {
        val jsonReader = JsonReader(FileReader(JSON_PATH))
        val songsList: List<SongJSONModel> = gson.fromJson(jsonReader)
        return Single.just(songsList)
    }

}