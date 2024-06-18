package uz.developersdreams.effectivemobile.feature.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.substringDate(): String {
    return this.substringAfter('T').substringBeforeLast(':')
}

fun Int.priceTransform(): String {
    return try {
        val symbols = DecimalFormatSymbols(Locale.US)
        val formatter = DecimalFormat("#,###.##", symbols)
        formatter.format(this).replace(',', ' ').plus(" ₽")
    }catch (_ : Exception) {
        ""
    }
}

fun Calendar.formatDate() : String {
    val df = SimpleDateFormat("dd-MMMM-EEEE", Locale.getDefault())
    val formatted = df.format(this.time).split('-')
    val stringBuilder = StringBuilder()

    return try {
        stringBuilder
            .append(formatted[0])
            .append(" ${formatted[1]},")
            .append(" ${formatted[formatted.lastIndex]}")

        stringBuilder.toString()
    }catch (_ : Exception) {
        df.format(this.time).toString()
    }
}

fun List<String>.extractTime() : String {
    val stringBuilder = StringBuilder()
    this.forEach { stringBuilder.append(it).append(" ") }
    return stringBuilder.toString()
}