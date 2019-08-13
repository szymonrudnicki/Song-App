package com.github.szymonrudnicki.songapp.data.model.mapper

import com.github.szymonrudnicki.songapp.data.model.SongJSONModel
import com.github.szymonrudnicki.songapp.data.model.SongsResponseModel
import com.github.szymonrudnicki.songapp.data.model.mapper.util.DateUtils
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel

object ModelMapper {
    fun mapSongsFromResponseToDomain(response: SongsResponseModel) = response.songs.map {
        SongDomainModel(it.trackName ?: "", it.artistName, DateUtils.parseYearFromDate(it.releaseDate))
    }

    fun mapSongsFromJSONToDomain(songs: List<SongJSONModel>) = songs.map {
        SongDomainModel(it.title, it.artist, it.releaseYear)
    }
}