package com.github.szymonrudnicki.songapp.app.injection

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.szymonrudnicki.songapp.app.common.Constants.BASE_API_URL
import com.github.szymonrudnicki.songapp.data.rest.ITunesApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = Kodein.Module(name = "api") {

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
                .addInterceptor(StethoInterceptor())
                .build()
    }

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .client(instance())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    bind<ITunesApiService>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(ITunesApiService::class.java)
    }

    bind<Gson>() with singleton {
        GsonBuilder().create()
    }
}