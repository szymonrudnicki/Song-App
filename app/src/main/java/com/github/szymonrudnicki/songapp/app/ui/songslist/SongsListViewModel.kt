package com.github.szymonrudnicki.songapp.app.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalAndRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsResult
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

class SongsListViewModel(
        private val getSongsFromLocalUseCase: GetSongsFromLocalUseCase,
        private val getSongsFromRemoteUseCase: GetSongsFromRemoteUseCase,
        private val getSongsFromLocalAndRemoteUseCase: GetSongsFromLocalAndRemoteUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val eventLiveData = MutableLiveData<SongsListUIEvent>()

    fun getSongsFromLocal() {
        compositeDisposable.add(
                getSongsFromLocalUseCase.execute()
                    .subscribe(::handleGetSongsResult, ::handleGetSongsError)
        )
    }

    fun getSongsFromRemote() {
        compositeDisposable.add(
                getSongsFromRemoteUseCase.execute()
                        .subscribe(::handleGetSongsResult, ::handleGetSongsError)
        )
    }

    fun getSongsFromLocalAndRemote() {
        compositeDisposable.add(
                getSongsFromLocalAndRemoteUseCase.execute()
                        .subscribe(::handleGetSongsResult, ::handleGetSongsError)
        )
    }

    private fun handleGetSongsResult(result: GetSongsResult) = when (result) {
        is GetSongsResult.Success ->
            eventLiveData.postValue(SongsListUIEvent.SongsChanged(result.songs))
        is GetSongsResult.Failed ->
            eventLiveData.postValue(SongsListUIEvent.Failed(Exception()))
    }

    private fun handleGetSongsError(throwable: Throwable) {
        eventLiveData.postValue(SongsListUIEvent.Failed(throwable))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}