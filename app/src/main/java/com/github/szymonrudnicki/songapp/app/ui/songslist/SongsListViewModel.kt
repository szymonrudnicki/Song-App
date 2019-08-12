package com.github.szymonrudnicki.songapp.app.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.szymonrudnicki.songapp.domain.pref.SharedPreferencesRepository
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalAndRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsResult
import io.reactivex.disposables.CompositeDisposable

class SongsListViewModel(
        private val sharedPreferencesRepository: SharedPreferencesRepository,
        private val getSongsFromLocalUseCase: GetSongsFromLocalUseCase,
        private val getSongsFromRemoteUseCase: GetSongsFromRemoteUseCase,
        private val getSongsFromLocalAndRemoteUseCase: GetSongsFromLocalAndRemoteUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val eventLiveData = MutableLiveData<SongsListUIEvent>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getSongsFromLastSource() {
        val lastCheckedSourceTag = sharedPreferencesRepository.getLastCheckedSourceTag()

        // on first app opening, there won't be any tag saved,
        // so temporarily Local source is set in this case
        // need more requirements to finish it
        val sourceTag = if (lastCheckedSourceTag.isNotEmpty()) {
            lastCheckedSourceTag
        } else {
            SourceType.Local.tag
        }

        handleSourceChoice(SourceType.getByTag(sourceTag))
    }

    fun handleSourceChoice(chosenSource: SourceType) {
        sharedPreferencesRepository.setLastCheckedSourceTag(chosenSource.tag)
        when (chosenSource) {
            SourceType.Local -> getSongsFromLocal()
            SourceType.Remote -> getSongsFromRemote()
            SourceType.LocalAndRemote -> getSongsFromLocalAndRemote()
        }
    }

    fun getLastCheckedSourceTag(): String =
            sharedPreferencesRepository.getLastCheckedSourceTag()

    private fun getSongsFromLocal() {
        loadingLiveData.postValue(true)
        compositeDisposable.add(
                getSongsFromLocalUseCase.execute()
                        .subscribe(::handleGetSongsResult, ::handleGetSongsError)
        )
    }

    private fun getSongsFromRemote() {
        loadingLiveData.postValue(true)
        compositeDisposable.add(
                getSongsFromRemoteUseCase.execute()
                        .subscribe(::handleGetSongsResult, ::handleGetSongsError)
        )
    }

    private fun getSongsFromLocalAndRemote() {
        loadingLiveData.postValue(true)
        compositeDisposable.add(
                getSongsFromLocalAndRemoteUseCase.execute()
                        .subscribe(::handleGetSongsResult, ::handleGetSongsError)
        )
    }

    private fun handleGetSongsResult(result: GetSongsResult) {
        loadingLiveData.postValue(false)
        return when (result) {
            is GetSongsResult.Success ->
                eventLiveData.postValue(SongsListUIEvent.SongsChanged(result.songs))
            is GetSongsResult.Failed ->
                eventLiveData.postValue(SongsListUIEvent.Failed(Exception()))
        }
    }

    private fun handleGetSongsError(throwable: Throwable) {
        loadingLiveData.postValue(false)
        eventLiveData.postValue(SongsListUIEvent.Failed(throwable))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}