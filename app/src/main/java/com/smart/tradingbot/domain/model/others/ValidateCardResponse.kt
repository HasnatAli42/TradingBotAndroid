package com.smart.tradingbot.domain.model.others

import com.smart.tradingbot.domain.model.singUpModelforJason.PaymentCardInfo

class ValidateCardResponse(): BaseModel() {

    var token: String = ""
    var response: PaymentCardInfo?=null
}