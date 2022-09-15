package com.example.tradingbot.domain.model.AccountInfoModel

data class AccountInfoResponseModel(
    val accountInfo : ArrayList<AccountInfoResponseModelItem>
)

class AccountInfoResponseModelApi : ArrayList<AccountInfoResponseModelItem>()

