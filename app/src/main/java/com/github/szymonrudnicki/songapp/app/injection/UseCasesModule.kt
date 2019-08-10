package com.github.szymonrudnicki.songapp.app.injection

import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalAndRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromRemoteUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val useCasesModule = Kodein.Module(name = "useCases") {
    bind<GetSongsFromLocalUseCase>() with provider { GetSongsFromLocalUseCase(instance()) }
    bind<GetSongsFromRemoteUseCase>() with provider { GetSongsFromRemoteUseCase(instance()) }
    bind<GetSongsFromLocalAndRemoteUseCase>() with provider { GetSongsFromLocalAndRemoteUseCase(instance()) }
}