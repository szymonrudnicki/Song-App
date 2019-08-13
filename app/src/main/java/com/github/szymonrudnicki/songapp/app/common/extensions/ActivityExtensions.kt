package com.github.szymonrudnicki.songapp.app.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : Fragment =
        lazy {
            ViewModelProviders.of(this, direct.instance()).get(VM::class.java)
        }

fun <T : Any?, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.removeObservers(this)
    liveData.observe(this, Observer(body))
}