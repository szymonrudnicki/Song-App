package com.github.szymonrudnicki.songapp.app

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.github.szymonrudnicki.songapp.app.injection.apiModule
import com.github.szymonrudnicki.songapp.app.injection.localModule
import com.github.szymonrudnicki.songapp.app.injection.songsModule
import com.github.szymonrudnicki.songapp.app.injection.useCasesModule
import com.github.szymonrudnicki.songapp.app.injection.viewmodel.viewModelModule
import net.danlew.android.joda.JodaTimeAndroid
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

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)
    }
}