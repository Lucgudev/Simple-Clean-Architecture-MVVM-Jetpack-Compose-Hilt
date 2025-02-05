package com.lucgu.pcstest.helper

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil {
    companion object {
        fun formatTimestamp(timestamp: String): String {
            try {
                val sdf = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())
                sdf.timeZone = TimeZone.getDefault() // Set system default time zone

                val inputSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                inputSdf.timeZone = TimeZone.getTimeZone("UTC") // Input is in UTC

                val date = inputSdf.parse(timestamp) // Parse the input timestamp
                return sdf.format(date!!) // Convert to readable format
            } catch (e: Exception) {
                return "-"
            }
        }
    }
}