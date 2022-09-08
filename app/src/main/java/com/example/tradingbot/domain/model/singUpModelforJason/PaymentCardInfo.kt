package com.example.tradingbot.domain.model.singUpModelforJason

class PaymentCardInfo{
    var cardholderName: String = ""
    var cardType: String = ""
    var cardNumber: String = ""
    var cvv: String = ""
    var expireDate: String = ""
    var isMailingAddress: Boolean = true
    var cardNonce: String? = ""
    var paymentGateway: String = "Square"
    var postalCode: String? = ""
    var customerId:String? = ""
    var customerCardId:String?= ""
}
