package com.github.szymonrudnicki.songapp.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalAndRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsResult
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
        private val getSongsFromLocalUseCase: GetSongsFromLocalUseCase,
        private val getSongsFromRemoteUseCase: GetSongsFromRemoteUseCase,
        private val getSongsFromLocalAndRemoteUseCase: GetSongsFromLocalAndRemoteUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val mainLiveData = MutableLiveData<MainUIEvent>()

    fun getSongsFromLocal() {
        compositeDisposable.add(
                getSongsFromLocalUseCase.execute()
                        .subscribe { result ->
                            when (result) {
                                is GetSongsResult.Success ->
                                    mainLiveData.postValue(MainUIEvent.SongsChanged(result.songs))
                            }
                        }
        )
    }

    fun getSongsFromRemote() {
        compositeDisposable.add(
                getSongsFromRemoteUseCase.execute()
                        .subscribe { result ->
                            when (result) {
                                is GetSongsResult.Success ->
                                    mainLiveData.postValue(MainUIEvent.SongsChanged(result.songs))
                            }
                        }
        )
    }

    fun getSongsFromLocalAndRemote() {
        compositeDisposable.add(
                getSongsFromLocalAndRemoteUseCase.execute()
                        .subscribe { result ->
                            when (result) {
                                is GetSongsResult.Success ->
                                    mainLiveData.postValue(MainUIEvent.SongsChanged(result.songs))
                            }
                        }
        )
    }
}