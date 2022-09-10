package com.example.tradingbot.domain.model.ProfileModel

data class ProfileResponseModel(
    val email: String,
    val first_name: String,
    val id: Int,
    val image_url: String,
    val last_name: String
)