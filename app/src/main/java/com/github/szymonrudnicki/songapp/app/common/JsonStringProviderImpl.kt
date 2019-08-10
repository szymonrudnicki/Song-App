package com.github.szymonrudnicki.songapp.app.common

import android.content.Context
import com.github.szymonrudnicki.songapp.R
import com.github.szymonrudnicki.songapp.data.json.JsonStringProvider

class JsonStringProviderImpl(private val context: Context) : JsonStringProvider {

    override fun get(): String =
            context.resources
                    .openRawResource(R.raw.songs_list)
                    .bufferedReader().use { it.readText() }
}