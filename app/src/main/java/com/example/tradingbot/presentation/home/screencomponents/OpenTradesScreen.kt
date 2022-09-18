package com.example.tradingbot.presentation.home.screencomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.functions.time.getDateTime
import com.example.tradingbot.domain.model.OpenOrdersResponseModel.OpenOrderResponseModel
import com.example.tradingbot.domain.model.OpenTradesResponseModel.OpenTradesResponseModel
import com.example.tradingbot.domain.use_cases.restapi.OpenOrdersApi
import com.example.tradingbot.domain.use_cases.restapi.OpenTradesApi
import com.example.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.example.tradingbot.presentation.home.components.OpenOrderCardView
import com.example.tradingbot.presentation.home.components.OpenTradesCardView

@Composable
fun OpenTradesScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
    openTradesResponseModel: MutableState<OpenTradesResponseModel>,
){
    val refresh = remember { mutableStateOf(true) }
    val openTradesResponseModelLocal = remember { mutableStateOf(OpenTradesResponseModel(openTrades = arrayListOf())) }

    if (refresh.value){
        refresh.value = false
        OpenTradesApi(
            protoViewModelLoginModel = protoViewModelLoginModel,
            progress = progress,
            openTradesResponseModel = openTradesResponseModelLocal,
            isFailureOccurred = isFailureOccurred,
            valueUpdateStatus = object : ValueUpdateStatus {
                override fun valueUpdateSuccessful() {
                    openTradesResponseModel.value = openTradesResponseModelLocal.value
                    refresh.value = true
                }
                override fun valueUpdateFailure() {
                    isFailureOccurred.value = true
                }
            }
        )
    }

    Column(verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .verticalScroll(rememberScrollState())
    )
    {
        if (openTradesResponseModel.value.openTrades.size > 0){
            openTradesResponseModel.value.openTrades.forEach { data ->
                Spacer(modifier = Modifier.padding(top= 5.dp))
                OpenTradesCardView(Data = data)
                Spacer(modifier = Modifier.padding(top= 5.dp))
            }
        } else {
            if (!refresh.value){
                Text(text = "Opps, No Open Trades Found", color = Color.Gray)
            }
        }

    }

}