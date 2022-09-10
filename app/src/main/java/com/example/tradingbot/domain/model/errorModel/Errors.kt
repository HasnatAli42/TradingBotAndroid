package com.example.tradingbot.domain.model.errorModel

data class Errors(
    val non_field_errors: List<String>,
    val detail:String
)