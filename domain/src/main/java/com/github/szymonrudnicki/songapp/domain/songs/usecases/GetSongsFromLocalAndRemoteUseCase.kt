package com.github.szymonrudnicki.songapp.domain.songs.usecases

import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository

class GetSongsFromLocalAndRemoteUseCase(private val songsRepository: SongsRepository) {
    fun execute() = songsRepository.getSongsFromLocalAndRemote()
}