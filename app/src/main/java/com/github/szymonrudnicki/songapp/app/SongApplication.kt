package com.github.szymonrudnicki.songapp.app

import android.app.Application
import android.content.Context
import com.github.szymonrudnicki.songapp.app.injection.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

class SongApplication : Application(), KodeinAware {
    override val kodein: Kodein by Kodein.lazy {
        bind<Context>() with instance(this@SongApplication)
        import(apiModule)
        import(songsModule)
        import(viewModelModule)
        import(useCasesModule)
        import(localModule)
    }
}