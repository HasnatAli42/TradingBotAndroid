package com.smart.tradingbot.domain.model.resetPasswordModel

data class ResetPaswordRequestModel(
    val password: String,
    val password2: String
)