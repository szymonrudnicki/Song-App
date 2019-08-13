package com.github.szymonrudnicki.songapp.app.injection

import android.content.Context
import android.content.SharedPreferences
import com.github.szymonrudnicki.songapp.data.json.SongsLocalSource
import com.github.szymonrudnicki.songapp.data.repositories.SharedPreferencesRepositoryImpl
import com.github.szymonrudnicki.songapp.data.repositories.SongsRepositoryImpl
import com.github.szymonrudnicki.songapp.data.rest.SongsRemoteSource
import com.github.szymonrudnicki.songapp.domain.pref.SharedPreferencesRepository
import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val songsModule = Kodein.Module(name = "songs") {
    bind<SongsRemoteSource>() with singleton { SongsRemoteSource(instance()) }
    bind<SongsLocalSource>() with singleton { SongsLocalSource(instance(), instance()) }
    bind<SongsRepository>() with singleton { SongsRepositoryImpl(instance(), instance()) }
    bind<SharedPreferences>() with singleton { instance<Context>().getSharedPreferences("", Context.MODE_PRIVATE) }
    bind<SharedPreferencesRepository>() with singleton { SharedPreferencesRepositoryImpl(instance()) }
}