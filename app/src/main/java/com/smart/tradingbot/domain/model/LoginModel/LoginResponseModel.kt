package com.smart.tradingbot.domain.model.LoginModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LoginResponseModel(
    val msg: String,
    val token: Token
) : Parcelable