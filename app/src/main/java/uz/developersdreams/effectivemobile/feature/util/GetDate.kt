package uz.developersdreams.effectivemobile.feature.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getDate(): String {
    val calendar = Calendar.getInstance().time
    val df = SimpleDateFormat("dd-MMMM-EEEE", Locale.getDefault())
    val formatted = df.format(calendar).split('-')
    val stringBuilder = StringBuilder()

    return try {
        stringBuilder
            .append(formatted[0])
            .append(" ${formatted[1]},")
            .append(" ${formatted[formatted.lastIndex]}")

        stringBuilder.toString()
    }catch (_ : Exception) {
        df.format(calendar).toString()
    }
}

fun getTimeDifference(
    dateOne: String,
    dateTwo: String
) : String {
    return try {
        // 2024-02-23T07:10:00 -> 07:10
        val from = dateOne.substringAfter('T').substringBeforeLast(':')
        val to = dateTwo.substringAfter('T').substringBeforeLast(':')

        // 07:10 -> 7
        val hour = countTime(
            from.substringBefore(':').toInt(),
            to.substringBefore(':').toInt(),
            23 // limit -> 24 hour
        )
        // 07:10 -> 10
        val minute = countTime(
            from.substringAfter(':').toInt(),
            to.substringAfter(':').toInt(),
            59 // limit -> 60 minute
        )
        ("${hour}.${minute}ч в пути")
    } catch (_ : Exception) {""}
}

/** CalculatesDifferenceOfTime -----------------------------------------------------------------! */
private fun countTime(from: Int, to: Int, limit: Int): String {
    if (from == to) return ""

    var tempFrom = from
    var counter = 0

    while (tempFrom != to) {
        tempFrom += 1
        counter += 1
        if (tempFrom > limit) {
            tempFrom = 0
        }
    }

    return counter.toString()
}