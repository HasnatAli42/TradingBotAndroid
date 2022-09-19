package com.smart.tradingbot.domain.model.OrderHistoryResponseModel

class OrderHistoryResponseModelApi : ArrayList<OrderHistoryResponseModelItem>()

data class OrderHistoryResponseModel (
    val orderHistory : ArrayList<OrderHistoryResponseModelItem>
        )