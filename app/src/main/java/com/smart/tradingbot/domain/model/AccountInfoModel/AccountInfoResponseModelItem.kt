package com.smart.tradingbot.domain.model.AccountInfoModel

data class AccountInfoResponseModelItem(
    val asset: String,
    val availableBalance: String,
    val crossUnPnl: String,
    val crossWalletBalance: String,
    val initialMargin: String,
    val maintMargin: String,
    val marginAvailable: Boolean,
    val marginBalance: String,
    val maxWithdrawAmount: String,
    val openOrderInitialMargin: String,
    val positionInitialMargin: String,
    val unrealizedProfit: String,
    val updateTime: Long,
    val walletBalance: String
)