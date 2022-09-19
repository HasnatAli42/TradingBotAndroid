package com.smart.tradingbot.domain.functions.time

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun getDateTime(s: Long): String? {
    return try {
        val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm a")
        val netDate = Date(s)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}

fun getDateTimeChangeFormat(s: String): String? {
    return try {
        val foundSDF = SimpleDateFormat("dd-MM-yyyy, hh:mm:ss")
        val convertedSFD = SimpleDateFormat("dd-MM-yyyy hh:mm a")
        val netDate = foundSDF.parse(s)
        convertedSFD.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}
