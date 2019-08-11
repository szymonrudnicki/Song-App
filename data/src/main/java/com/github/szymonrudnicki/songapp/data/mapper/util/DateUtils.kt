package com.github.szymonrudnicki.songapp.data.mapper.util

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateUtils {
    fun parseYearFromDate(date: String): String {
        val dateTime = DateTime(date)
        val yearFormat = DateTimeFormat.forPattern("yyyy")
        return yearFormat.print(dateTime)
    }
}