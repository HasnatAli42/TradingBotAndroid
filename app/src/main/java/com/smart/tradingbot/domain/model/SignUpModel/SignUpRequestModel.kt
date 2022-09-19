package com.smart.tradingbot.domain.model.SignUpModel

data class SignUpRequestModel(
    val api_key: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val password2: String,
    val secret_key: String
)