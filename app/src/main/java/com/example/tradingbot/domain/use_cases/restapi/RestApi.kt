package com.example.tradingbot.domain.use_cases.restapi

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.use_cases.cronet.logincronet.loginCronet
import com.example.tradingbot.domain.functions.callbacks.ApiCallBack
import com.example.tradingbot.domain.functions.callbacks.ValueUpdateStatusLogin
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModel
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelApi
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem
import com.example.tradingbot.domain.model.LoginModel.LoginRequestModel
import com.example.tradingbot.domain.model.LoginModel.LoginResponseModel
import com.example.tradingbot.domain.model.OpenOrdersResponseModel.OpenOrderResponseModel
import com.example.tradingbot.domain.model.OpenOrdersResponseModel.OpenOrderResponseModelApi
import com.example.tradingbot.domain.model.OrderHistoryResponseModel.OrderHistoryResponseModel
import com.example.tradingbot.domain.model.OrderHistoryResponseModel.OrderHistoryResponseModelApi
import com.example.tradingbot.domain.model.ProfileModel.ProfileResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModelApi
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModelItem
import com.example.tradingbot.domain.model.errorModel.ErrorModel
import com.example.tradingbot.presentation.main.MainActivity
import com.example.tradingbot.ui.theme.*
import com.google.gson.Gson
import org.chromium.base.ThreadUtils.runOnUiThread




@Composable
fun LoginApi(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    email: String,
    password: String,
    rememberMe : Boolean,
    isFailureOccurred : MutableState<Boolean>,
    valueUpdateStatus: ValueUpdateStatus,
    intent: Intent,
    message : MutableState<String>,
) {
    val param = LoginRequestModel(email = email,password= password)
    val context = LocalContext.current as ComponentActivity
    val request = loginCronet()
    protoViewModelLoginModel.tokenLiveData.observe(context) {
        progress.value = true
        val json = Gson().toJson(param)
        request.sendParametersOnlyRequest(
            loginUrl,
            json,
            object : ApiCallBack {
                override fun onSuccess(byteArray: ByteArray, httpsStatusCode: Int) {
                    progress.value = false
                    val response = String(byteArray)
                    if(httpsStatusCode == 200){
                        val responseModel =
                            Gson().fromJson(response, LoginResponseModel::class.java)
                        if (responseModel.msg == "Login Success" && httpsStatusCode == 200) {
                            displayErrorMsg(context, responseModel.msg.toString())
                            protoViewModelLoginModel.updateValueLogin(
                                email,
                                password
                            )
                            protoViewModelLoginModel.updateValueSecurityToken(
                                responseModel.token.access,
                                rememberMe
                            )
                            intent.putExtra("parsedResponse", responseModel)
                            valueUpdateStatus.valueUpdateSuccessful()
                        }
                    } else {
                        val responseModel =
                            Gson().fromJson(response, ErrorModel::class.java)
                        displayErrorMsg(context, responseModel.errors.non_field_errors.toString())
                    }
                }

                override fun onFailure() {
                    progress.value = false
                    isFailureOccurred.value = true
                    valueUpdateStatus.valueUpdateFailure()
                }
            })
    }
}


@Composable
fun ProfileApi(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    profileResponseModel: MutableState<ProfileResponseModel>,
    isFailureOccurred : MutableState<Boolean>,
    valueUpdateStatus: ValueUpdateStatus
) {
    val context = LocalContext.current as ComponentActivity
    val request = loginCronet()
    protoViewModelLoginModel.tokenLiveData.observe(context) {
        progress.value = true
        request.sendSecurityTokenOnlyRequest(
            profileUrl,
            it.securityToken.toString(),
            object : ApiCallBack {
                override fun onSuccess(byteArray: ByteArray, httpsStatusCode: Int) {
                    progress.value = false
                    val response = String(byteArray)
                    if (httpsStatusCode == 200) {
                        val responseModel =
                            Gson().fromJson(response, ProfileResponseModel::class.java)
                        profileResponseModel.value = responseModel
                        valueUpdateStatus.valueUpdateSuccessful()
                    } else {
                        val responseModel =
                            Gson().fromJson(response, ErrorModel::class.java)
                        displayErrorMsg(context, responseModel.errors.detail.toString())
                    }
                }

                override fun onFailure() {
                    progress.value = false
                    isFailureOccurred.value = true
                    valueUpdateStatus.valueUpdateFailure()
                }
            })
    }
}


@Composable
fun AccountInfoApi(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    accountInfoResponseModel: MutableState<AccountInfoResponseModel>,
    isFailureOccurred : MutableState<Boolean>,
    valueUpdateStatus: ValueUpdateStatus
) {
    val context = LocalContext.current as ComponentActivity
    val request = loginCronet()
    protoViewModelLoginModel.tokenLiveData.observe(context) {
        progress.value = true
        request.sendSecurityTokenOnlyRequest(
            accountInfoUrl,
            it.securityToken.toString(),
            object : ApiCallBack {
                override fun onSuccess(byteArray: ByteArray, httpsStatusCode: Int) {
                    progress.value = false
                    val response = String(byteArray)
                    if (httpsStatusCode == 200) {
                        val responseModel =
                            Gson().fromJson(response, AccountInfoResponseModelApi::class.java)
                        accountInfoResponseModel.value.accountInfo.clear()
                        if (responseModel.size > 0) {
                            accountInfoResponseModel.value.accountInfo.addAll(responseModel)
                        }
                        valueUpdateStatus.valueUpdateSuccessful()
                    } else {
                        val responseModel =
                            Gson().fromJson(response, ErrorModel::class.java)
                        displayErrorMsg(context, responseModel.errors.detail.toString())
                    }
                }
                override fun onFailure() {
                    progress.value = false
                    isFailureOccurred.value = true
                    valueUpdateStatus.valueUpdateFailure()
                }
            })
    }
}

@Composable
fun TradeHistoryApi(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    tradeHistoryResponseModel: MutableState<TradeHistoryResponseModel>,
    isFailureOccurred : MutableState<Boolean>,
    valueUpdateStatus: ValueUpdateStatus
) {
    val context = LocalContext.current as ComponentActivity
    val request = loginCronet()
    protoViewModelLoginModel.tokenLiveData.observe(context) {
        progress.value = true
        request.sendSecurityTokenOnlyRequest(
            tradeHistoryUrl,
            it.securityToken.toString(),
            object : ApiCallBack {
                override fun onSuccess(byteArray: ByteArray, httpsStatusCode: Int) {
                    progress.value = false
                    val response = String(byteArray)
                    if (httpsStatusCode == 200) {
                        val responseModel =
                            Gson().fromJson(response, TradeHistoryResponseModelApi::class.java)
                        tradeHistoryResponseModel.value.tradeHistory.clear()
                        if (responseModel.size > 0) {
                            tradeHistoryResponseModel.value.tradeHistory.addAll(responseModel)
                        }
                        valueUpdateStatus.valueUpdateSuccessful()
                    } else {
                        val responseModel =
                            Gson().fromJson(response, ErrorModel::class.java)
                        displayErrorMsg(context, responseModel.errors.detail.toString())
                    }
                }
                override fun onFailure() {
                    progress.value = false
                    isFailureOccurred.value = true
                    valueUpdateStatus.valueUpdateFailure()
                }
            })
    }
}

@Composable
fun OrderHistoryApi(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    orderHistoryResponseModel: MutableState<OrderHistoryResponseModel>,
    Symbol : String,
    isFailureOccurred : MutableState<Boolean>,
    valueUpdateStatus: ValueUpdateStatus
) {
    val context = LocalContext.current as ComponentActivity
    val request = loginCronet()
    protoViewModelLoginModel.tokenLiveData.observe(context) {
        progress.value = true
        request.sendSecurityTokenOnlyRequest(
            "$orderHistoryUrl$Symbol/",
            it.securityToken.toString(),
            object : ApiCallBack {
                override fun onSuccess(byteArray: ByteArray, httpsStatusCode: Int) {
                    progress.value = false
                    val response = String(byteArray)
                    if (httpsStatusCode == 200) {
                        val responseModel =
                            Gson().fromJson(response, OrderHistoryResponseModelApi::class.java)
                        orderHistoryResponseModel.value.orderHistory.clear()
                        if (responseModel.size > 0){
                            orderHistoryResponseModel.value.orderHistory.addAll(responseModel)
                        }
                        valueUpdateStatus.valueUpdateSuccessful()
                    } else {
                        val responseModel =
                            Gson().fromJson(response, ErrorModel::class.java)
                        displayErrorMsg(context, responseModel.errors.detail.toString())
                    }
                }
                override fun onFailure() {
                    progress.value = false
                    isFailureOccurred.value = true
                    valueUpdateStatus.valueUpdateFailure()
                }
            })
    }
}


@Composable
fun OpenOrdersApi(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    openOrdersResponseModel: MutableState<OpenOrderResponseModel>,
    isFailureOccurred : MutableState<Boolean>,
    valueUpdateStatus: ValueUpdateStatus
) {
    val context = LocalContext.current as ComponentActivity
    val request = loginCronet()
    protoViewModelLoginModel.tokenLiveData.observe(context) {
        progress.value = true
        request.sendSecurityTokenOnlyRequest(
            openOrdersUrl,
            it.securityToken.toString(),
            object : ApiCallBack {
                override fun onSuccess(byteArray: ByteArray, httpsStatusCode: Int) {
                    progress.value = false
                    val response = String(byteArray)
                    if (httpsStatusCode == 200) {
                        val responseModel =
                            Gson().fromJson(response, OpenOrderResponseModelApi::class.java)
                        openOrdersResponseModel.value.openOrders.clear()
                        if (responseModel.size > 0){
                            openOrdersResponseModel.value.openOrders.addAll(responseModel)
                        }
                        valueUpdateStatus.valueUpdateSuccessful()
                    } else {
                        val responseModel =
                            Gson().fromJson(response, ErrorModel::class.java)
                        displayErrorMsg(context, responseModel.errors.detail.toString())
                    }
                }
                override fun onFailure() {
                    progress.value = false
                    isFailureOccurred.value = true
                    valueUpdateStatus.valueUpdateFailure()
                }
            })
    }
}

fun displayErrorMsg(context: Context,errorMsg:String){
    runOnUiThread {
        Toast.makeText(
            context,
            errorMsg,
            Toast.LENGTH_LONG
        ).show()
    }

}


fun securityTokenExpired(context: Context, protoViewModelLoginModel: ProtoViewModelLoginModel,isRemembered : Boolean){
    runOnUiThread {
        Toast.makeText(
            context,
            sessionExpired,
            Toast.LENGTH_LONG
        ).show()
        protoViewModelLoginModel.updateValueSecurityToken("",isRemembered)
        var intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

}




interface ValueUpdateStatus{
    fun valueUpdateSuccessful()
    fun valueUpdateFailure()
}

abstract class ValueUpdate(){
    fun valueUpdate(){}
    abstract fun valueUpdate1();
}

open class Parent(){
    var a = 1
    var b = 2
}

class Child :Parent(){
    var c = 3
    var d = 4
}


 fun aFunction(){
     lateinit var p : Parent
     p = Child()
     Log.d("Function",p.toString())
     Log.d("Function",p.a.toString())
 }


