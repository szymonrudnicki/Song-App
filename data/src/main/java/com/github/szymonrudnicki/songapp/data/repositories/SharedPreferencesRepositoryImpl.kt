package com.github.szymonrudnicki.songapp.data.repositories

import android.content.SharedPreferences
import com.github.szymonrudnicki.songapp.domain.pref.SharedPreferencesRepository

const val LAST_CHECKED_SOURCE_TAG_KEY = "last_checked_source_tag"

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SharedPreferencesRepositoryImpl(private val sharedPreferences: SharedPreferences) : SharedPreferencesRepository {

    override fun getLastCheckedSourceTag(): String =
            sharedPreferences.getString(LAST_CHECKED_SOURCE_TAG_KEY, "")

    override fun setLastCheckedSourceTag(tag: String) {
        sharedPreferences.edit().putString(LAST_CHECKED_SOURCE_TAG_KEY, tag).apply()
    }
}