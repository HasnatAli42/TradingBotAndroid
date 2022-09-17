package com.example.tradingbot.domain.model.TradeHistoryModel

import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem


data class TradeHistoryResponseModel(
    val tradeHistory : ArrayList<TradeHistoryResponseModelItem>
)

class TradeHistoryResponseModelApi : ArrayList<TradeHistoryResponseModelItem>()