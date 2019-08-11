package com.github.szymonrudnicki.songapp.app.rx

import com.github.szymonrudnicki.songapp.domain.common.usecases.SchedulersFacade
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulers : SchedulersFacade {
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
}