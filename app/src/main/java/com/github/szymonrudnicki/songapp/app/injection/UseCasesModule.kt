package com.github.szymonrudnicki.songapp.app.injection

import com.github.szymonrudnicki.songapp.app.rx.AppSchedulers
import com.github.szymonrudnicki.songapp.domain.common.usecases.SchedulersFacade
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalAndRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromRemoteUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val useCasesModule = Kodein.Module(name = "useCases") {
    bind<SchedulersFacade>() with singleton { AppSchedulers() }
    bind<GetSongsFromLocalUseCase>() with provider {
        GetSongsFromLocalUseCase(instance(), instance())
    }
    bind<GetSongsFromRemoteUseCase>() with provider {
        GetSongsFromRemoteUseCase(instance(), instance())
    }
    bind<GetSongsFromLocalAndRemoteUseCase>() with provider {
        GetSongsFromLocalAndRemoteUseCase(instance(), instance())
    }
}