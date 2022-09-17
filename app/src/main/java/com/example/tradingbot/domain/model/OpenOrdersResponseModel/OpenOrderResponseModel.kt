package com.example.tradingbot.domain.model.OpenOrdersResponseModel

class OpenOrderResponseModelApi : ArrayList<OpenOrderResponseModelItem>()

data class OpenOrderResponseModel (
    val openOrders : ArrayList<OpenOrderResponseModelItem>
    )
