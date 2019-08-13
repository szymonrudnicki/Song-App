package com.github.szymonrudnicki.songapp.domain.pref

interface SharedPreferencesRepository {
    fun getLastCheckedSourceTag(): String
    fun setLastCheckedSourceTag(tag: String)
}