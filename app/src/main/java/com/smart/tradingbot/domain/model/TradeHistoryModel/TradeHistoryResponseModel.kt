package com.smart.tradingbot.domain.model.TradeHistoryModel


data class TradeHistoryResponseModel(
    val tradeHistory : ArrayList<TradeHistoryResponseModelItem>
)

class TradeHistoryResponseModelApi : ArrayList<TradeHistoryResponseModelItem>()