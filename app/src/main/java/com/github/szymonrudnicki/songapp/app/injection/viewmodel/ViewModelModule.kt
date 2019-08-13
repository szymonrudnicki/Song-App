package com.github.szymonrudnicki.songapp.app.injection.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.github.szymonrudnicki.songapp.app.common.extensions.bindViewModel
import com.github.szymonrudnicki.songapp.app.ui.songslist.SongsListViewModel
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val viewModelModule = Kodein.Module(name = "viewModel") {
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
    bindViewModel<SongsListViewModel>() with provider { SongsListViewModel(instance(), instance(), instance(), instance()) }
}