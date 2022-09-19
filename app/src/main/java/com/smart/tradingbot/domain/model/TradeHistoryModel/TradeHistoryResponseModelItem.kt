package com.smart.tradingbot.domain.model.TradeHistoryModel

data class TradeHistoryResponseModelItem(
    val buyer: Boolean,
    val commission: String,
    val commissionAsset: String,
    val id: Int,
    val maker: Boolean,
    val marginAsset: String,
    val orderId: Long,
    val positionSide: String,
    val price: String,
    val qty: String,
    val quoteQty: String,
    val realizedPnl: String,
    val side: String,
    val symbol: String,
    val time: Long,
    var dateTime : String?
)