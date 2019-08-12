package com.github.szymonrudnicki.songapp.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.github.szymonrudnicki.songapp.domain.pref.SharedPreferencesRepository

private const val LAST_CHECKED_SOURCE_TAG_KEY = "last_checked_source_tag"

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SharedPreferencesRepositoryImpl(context: Context) : SharedPreferencesRepository {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE)

    override fun getLastCheckedSourceTag(): String =
            sharedPreferences.getString(LAST_CHECKED_SOURCE_TAG_KEY, "")

    override fun setLastCheckedSourceTag(tag: String) {
        sharedPreferences.edit().putString(LAST_CHECKED_SOURCE_TAG_KEY, tag).apply()
    }

}