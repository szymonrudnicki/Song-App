package com.github.szymonrudnicki.songapp.app.injection

import com.github.szymonrudnicki.songapp.app.common.Constants
import com.github.szymonrudnicki.songapp.data.rest.ITunesApiService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val apiModule = Kodein.Module(name = "api") {

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .build()
    }

    bind<ITunesApiService>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(ITunesApiService::class.java)
    }
}