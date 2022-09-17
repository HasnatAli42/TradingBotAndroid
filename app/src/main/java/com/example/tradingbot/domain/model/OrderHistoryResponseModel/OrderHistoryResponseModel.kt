package com.example.tradingbot.domain.model.OrderHistoryResponseModel

class OrderHistoryResponseModelApi : ArrayList<OrderHistoryResponseModelItem>()

data class OrderHistoryResponseModel (
    val orderHistory : ArrayList<OrderHistoryResponseModelItem>
        )