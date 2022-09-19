package com.smart.tradingbot.domain.model.LoginModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Token(
    val access: String,
    val refresh: String
) : Parcelable