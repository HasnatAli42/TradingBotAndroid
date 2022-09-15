package com.example.tradingbot.domain.functions.time

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun getDateTime(s: Long): String? {
    return try {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val netDate = Date(s)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}