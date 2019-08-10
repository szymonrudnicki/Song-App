package com.github.szymonrudnicki.songapp.app.injection

import androidx.lifecycle.ViewModelProvider
import com.github.szymonrudnicki.songapp.app.MainViewModel
import com.github.szymonrudnicki.songapp.app.common.extensions.bindViewModel
import com.github.szymonrudnicki.songapp.app.injection.viewmodel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val viewModelModule = Kodein.Module(name = "viewModel") {
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
    bindViewModel<MainViewModel>() with provider { MainViewModel(instance(), instance(), instance()) }
}