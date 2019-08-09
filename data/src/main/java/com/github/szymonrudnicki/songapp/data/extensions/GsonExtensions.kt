package com.github.szymonrudnicki.songapp.data.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

inline fun <reified T> Gson.fromJson(jsonReader: JsonReader) = this.fromJson<T>(jsonReader, object: TypeToken<T>() {}.type)