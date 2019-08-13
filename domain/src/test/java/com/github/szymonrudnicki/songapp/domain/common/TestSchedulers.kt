package com.github.szymonrudnicki.songapp.domain.common

import com.github.szymonrudnicki.songapp.domain.common.usecases.SchedulersFacade
import io.reactivex.schedulers.Schedulers

class TestSchedulers : SchedulersFacade {
    override fun main() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}