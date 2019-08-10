package com.github.szymonrudnicki.songapp.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val mainLiveData = MutableLiveData<MainUIEvent>()

    fun sayHi() {
        mainLiveData.postValue(MainUIEvent.Hi)
    }

}