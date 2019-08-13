package com.github.szymonrudnicki.songapp.app.injection

import com.github.szymonrudnicki.songapp.app.common.JsonStringProviderImpl
import com.github.szymonrudnicki.songapp.data.json.JsonStringProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val localModule = Kodein.Module(name = "local") {
    bind<JsonStringProvider>() with singleton { JsonStringProviderImpl(instance()) }
}