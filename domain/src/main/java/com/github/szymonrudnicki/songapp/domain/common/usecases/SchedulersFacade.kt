package com.github.szymonrudnicki.songapp.domain.common.usecases

import io.reactivex.Scheduler

interface SchedulersFacade {
    fun main(): Scheduler
    fun io(): Scheduler
}