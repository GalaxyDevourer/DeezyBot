package utils

import java.text.SimpleDateFormat
import java.util.*

class ISODateFormatter {
    fun format(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val dateTime = sdf.parse(date)
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")

        return formatter.format(dateTime)
    }
}