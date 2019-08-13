package com.github.szymonrudnicki.songapp.data

import com.github.szymonrudnicki.songapp.data.model.SongJSONModel
import com.github.szymonrudnicki.songapp.data.model.mapper.ModelMapper
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import org.junit.Test

class ModelMapperTest {

    @Test
    fun shouldCorrectlyMapExampleSongsFromJSONToDomain() {
        val jsonSongs = listOf(
                SongJSONModel(artist = "Artist1", releaseYear = "1995", title = "Title1", combinedTitleWithArtist = "", fg = 0, first = 0, playCount = 0, year = 0),
                SongJSONModel(artist = "Artist2", releaseYear = "1996", title = "Title2", combinedTitleWithArtist = "", fg = 0, first = 0, playCount = 0, year = 0),
                SongJSONModel(artist = "Artist3", releaseYear = "1997", title = "Title3", combinedTitleWithArtist = "", fg = 0, first = 0, playCount = 0, year = 0)
        )
        val domainSongs = listOf(
                SongDomainModel(artist = "Artist1", releaseYear = "1995", title = "Title1"),
                SongDomainModel(artist = "Artist2", releaseYear = "1996", title = "Title2"),
                SongDomainModel(artist = "Artist3", releaseYear = "1997", title = "Title3")
        )

        assert(ModelMapper.mapSongsFromJSONToDomain(jsonSongs) == domainSongs)
    }
}
