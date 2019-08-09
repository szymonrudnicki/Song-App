package com.github.szymonrudnicki.songapp.data.mapper

import com.github.szymonrudnicki.songapp.data.json.SongJSONModel
import com.github.szymonrudnicki.songapp.data.rest.SongResponseModel
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel

object ModelMapper {
    fun mapSongsFromResponseToDomain(songs: List<SongResponseModel>) = songs.map {
        SongDomainModel("todo", "todo", 1337)
    }

    fun mapSongsFromJSONToDomain(songs: List<SongJSONModel>) = songs.map {
        SongDomainModel(it.title, it.artist, it.releaseYear)
    }
}