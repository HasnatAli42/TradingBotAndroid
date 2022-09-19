package com.smart.tradingbot.domain.model.OpenTradesResponseModel

data class OpenTradesResponseModelItem(
    val entryPrice: String,
    val isAutoAddMargin: String,
    val isolatedMargin: String,
    val isolatedWallet: String,
    val leverage: String,
    val liquidationPrice: String,
    val marginType: String,
    val markPrice: String,
    val maxNotionalValue: String,
    val notional: String,
    val positionAmt: String,
    val positionSide: String,
    val symbol: String,
    val unRealizedProfit: String,
    val updateTime: Long
)