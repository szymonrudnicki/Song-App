package com.github.szymonrudnicki.songapp.domain.songs.usecases

import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository

class GetSongsFromLocalUseCase(
        private val songsRepository: SongsRepository
) {
        fun execute() = songsRepository.getSongsFromLocal()
}