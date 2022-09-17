package com.example.tradingbot.domain.model.OrderHistoryResponseModel

data class OrderHistoryResponseModelItem(
    val avgPrice: String,
    val clientOrderId: String,
    val closePosition: Boolean,
    val cumQuote: String,
    val executedQty: String,
    val orderId: Long,
    val origQty: String,
    val origType: String,
    val positionSide: String,
    val price: String,
    val priceProtect: Boolean,
    val reduceOnly: Boolean,
    val side: String,
    val status: String,
    val stopPrice: String,
    val symbol: String,
    val time: String,
    var dateTime: String?,
    val timeInForce: String,
    val type: String,
    val updateTime: Long,
    val workingType: String
)