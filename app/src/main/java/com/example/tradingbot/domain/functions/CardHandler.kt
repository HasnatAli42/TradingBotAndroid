package com.example.tradingbot.domain.functions


//@Composable
//fun validateCardRecursion(payment: paymentCardDic){
//        val context = LocalContext.current as AppCompatActivity
////        showProgress()
//        validateCreditCard(payment, object : ValidateCardCallBack {
//            override fun onValidateCardPosted(validateCardResponse: ValidateCardResponse) {
//                runOnUiThread {
////                    hideProgress()
//                    if (validateCardResponse != null) {
//
//                        if (validateCardResponse.success == 1) {
//                            Toast.makeText(
//                                context,
//                                "Card Signed Up Successfully",
//                                Toast.LENGTH_LONG
//                            ).show()
//
//                            setSignInResponse(validateCardResponse)
//
//                        }else{
////                            showWarningAlerts(context,object : WarningsCallBack {
////                                @Composable
////                                override fun onRetry() {
////                                    validateCardRecursion(payment)
////                                }
////
////                            },validateCardResponse.message)
//                        }
//                    }
//
//                }
//            }
//
//            override fun onFailure(validateCardResponse: ValidateCardResponse) {
//                runOnUiThread {
////                    hideProgress()
//                    Toast.makeText(
//                        context,
//                        "Some Error",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//
//        })
//    }
//
////    fun showProgress() {
////        body.visibility = View.GONE`
////        progressBar.visibility = View.VISIBLE
////    }
////
////    fun hideProgress() {
////        body.visibility = View.VISIBLE
////        progressBar.visibility = View.GONE
////    }
//}