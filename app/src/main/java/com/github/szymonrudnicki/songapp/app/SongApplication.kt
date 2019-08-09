package com.github.szymonrudnicki.songapp.app

import android.app.Application
import com.github.szymonrudnicki.songapp.app.injection.apiModule
import com.github.szymonrudnicki.songapp.app.injection.songsModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class SongApplication : Application(), KodeinAware {
    override val kodein: Kodein by Kodein.lazy {
        import(apiModule)
        import(songsModule)
    }
}