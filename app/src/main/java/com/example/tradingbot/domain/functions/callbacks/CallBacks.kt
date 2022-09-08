package com.example.tradingbot.domain.functions.callbacks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tradingbot.domain.model.LoginModel.LoginResponseModel
import com.example.tradingbot.ui.theme.YellowishRed

interface ApiCallBack {
    fun onSuccess(byteArray: ByteArray, httpsStatusCode :Int)
    fun onFailure()
}

interface ValueUpdateStatusLogin{
    fun valueUpdateSuccessful(loginResponse : LoginResponseModel)
    fun valueUpdateFailure()
}

@Composable
fun ProgressBar() {
    Column(modifier = Modifier.fillMaxWidth(1f)) {
    }
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.Center) {
        CircularProgressIndicator(color = YellowishRed)
    }


}