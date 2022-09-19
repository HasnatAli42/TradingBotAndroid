package com.smart.tradingbot.domain.model.OpenTradesResponseModel

class OpenTradesResponseModelApi : ArrayList<OpenTradesResponseModelItem>()

data class OpenTradesResponseModel (
   val openTrades : ArrayList<OpenTradesResponseModelItem>
)