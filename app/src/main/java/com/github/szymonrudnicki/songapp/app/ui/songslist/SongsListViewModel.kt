package com.github.szymonrudnicki.songapp.app.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.szymonrudnicki.songapp.domain.pref.SharedPreferencesRepository
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalAndRemoteUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalUseCase
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromRemoteUseCase
import io.reactivex.disposables.CompositeDisposable

class SongsListViewModel(
        private val sharedPreferencesRepository: SharedPreferencesRepository,
        private val getSongsFromLocalUseCase: GetSongsFromLocalUseCase,
        private val getSongsFromRemoteUseCase: GetSongsFromRemoteUseCase,
        private val getSongsFromLocalAndRemoteUseCase: GetSongsFromLocalAndRemoteUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val songsLiveData = MutableLiveData<List<SongDomainModel>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val toastLiveData = MutableLiveData<String>()

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

    fun getLastCheckedSourceTag(): String = sharedPreferencesRepository.getLastCheckedSourceTag()

    private fun getSongsFromLocal() {
        loadingLiveData.postValue(true)
        compositeDisposable.add(
                getSongsFromLocalUseCase.execute()
                        .subscribe(::handleSongs, ::handleGetSongsError)
        )
    }

    private fun getSongsFromRemote() {
        loadingLiveData.postValue(true)
        compositeDisposable.add(
                getSongsFromRemoteUseCase.execute()
                        .subscribe(::handleSongs, ::handleGetSongsError)
        )
    }

    private fun getSongsFromLocalAndRemote() {
        loadingLiveData.postValue(true)
        compositeDisposable.add(
                getSongsFromLocalAndRemoteUseCase.execute()
                        .subscribe(::handleSongs, ::handleGetSongsError)
        )
    }

    private fun handleSongs(songs: List<SongDomainModel>) {
        loadingLiveData.postValue(false)
        songsLiveData.postValue(songs)
    }

    private fun handleGetSongsError(throwable: Throwable) {
        loadingLiveData.postValue(false)
        songsLiveData.postValue(listOf())
        toastLiveData.postValue("Something went wrong.")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}