package utils

class DurationFormatter {
    fun duration(seconds: Int): String {
        val minFormatted = seconds / 60;
        val sec = seconds - minFormatted*60
        val secFormatted = if (sec.toString().length == 1) "0$sec"
        else sec

        return "$minFormatted:$secFormatted"
    }
}