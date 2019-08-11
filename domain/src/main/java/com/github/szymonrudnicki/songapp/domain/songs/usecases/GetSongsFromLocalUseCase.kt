package com.github.szymonrudnicki.songapp.domain.songs.usecases

import com.github.szymonrudnicki.songapp.domain.common.usecases.SchedulersFacade
import com.github.szymonrudnicki.songapp.domain.common.usecases.SingleUseCase
import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository
import io.reactivex.Single

class GetSongsFromLocalUseCase(
        private val songsRepository: SongsRepository,
        schedulersFacade: SchedulersFacade
) : SingleUseCase<GetSongsResult, Unit>(schedulersFacade) {

    override fun buildUseCaseSingle(params: Unit?): Single<GetSongsResult> =
            songsRepository.getSongsFromLocal().subscribeOnIO()
}