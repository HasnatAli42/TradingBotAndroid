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
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModel
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem
import com.example.tradingbot.domain.model.OpenOrdersResponseModel.OpenOrderResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModelItem
import com.example.tradingbot.domain.use_cases.restapi.AccountInfoApi
import com.example.tradingbot.domain.use_cases.restapi.OpenOrdersApi
import com.example.tradingbot.domain.use_cases.restapi.TradeHistoryApi
import com.example.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.example.tradingbot.presentation.home.components.AccountInFoCardView
import com.example.tradingbot.presentation.home.components.OpenOrderCardView
import com.example.tradingbot.presentation.home.components.TradeHistoryCardView

@Composable
fun OpenOrdersScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
    openOrdersResponseModel: MutableState<OpenOrderResponseModel>,
){
    val refresh = remember { mutableStateOf(true) }
    val openOrdersResponseModelLocal = remember { mutableStateOf(OpenOrderResponseModel(openOrders = arrayListOf())) }

    if (refresh.value){
        refresh.value = false
        OpenOrdersApi(
            protoViewModelLoginModel = protoViewModelLoginModel,
            progress = progress,
            openOrdersResponseModel = openOrdersResponseModelLocal,
            isFailureOccurred = isFailureOccurred,
            valueUpdateStatus = object : ValueUpdateStatus {
                override fun valueUpdateSuccessful() {
                    openOrdersResponseModelLocal.value.openOrders.sortByDescending {
                        it.dateTime = getDateTime(it.time)
                        it.time
                    }
                    openOrdersResponseModel.value = openOrdersResponseModelLocal.value
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
        if (openOrdersResponseModel.value.openOrders.size > 0){
            openOrdersResponseModel.value.openOrders.forEach { data ->
                Spacer(modifier = Modifier.padding(top= 5.dp))
                OpenOrderCardView(Data = data)
                Spacer(modifier = Modifier.padding(top= 5.dp))
            }
        } else {
            if (!progress.value){
                Text(text = "Opps, No Open Orders Found", color = Color.Gray)
            }
        }

    }

}