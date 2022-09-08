package com.example.tradingbot.domain.model.others

import com.example.tradingbot.domain.model.singUpModelforJason.PaymentCardInfo

class ValidateCardResponse(): BaseModel() {

    var token: String = ""
    var response: PaymentCardInfo?=null
}