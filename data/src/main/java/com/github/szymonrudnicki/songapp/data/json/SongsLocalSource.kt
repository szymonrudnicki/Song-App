package com.github.szymonrudnicki.songapp.data.json

import com.github.szymonrudnicki.songapp.data.extensions.fromJson
import com.github.szymonrudnicki.songapp.data.model.SongJSONModel
import com.google.gson.Gson
import io.reactivex.Single

class SongsLocalSource(
    private val gson: Gson,
    private val jsonStringProvider: JsonStringProvider
) {

    fun getSongs(): Single<List<SongJSONModel>> {
        val jsonString = jsonStringProvider.get()
        val songsList: List<SongJSONModel> = gson.fromJson(jsonString)
        return Single.just(songsList)
    }
}