package com.example.tradingbot.domain.model.others

data class InboxMsgClass(
    var id: Int,
    var title: String,
    var time: String,
    var msgDescription: String,
    var msgImageId: Int = 0
)