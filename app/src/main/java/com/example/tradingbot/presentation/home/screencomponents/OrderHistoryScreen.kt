package com.example.tradingbot.presentation.home.screencomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import com.example.tradingbot.domain.functions.time.getDateTimeChangeFormat
import com.example.tradingbot.domain.model.OrderHistoryResponseModel.OrderHistoryResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModel
import com.example.tradingbot.domain.use_cases.restapi.OrderHistoryApi
import com.example.tradingbot.domain.use_cases.restapi.TradeHistoryApi
import com.example.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.example.tradingbot.presentation.home.components.TradeHistoryCardView

@Composable
fun OrderHistoryScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
    symbol : MutableState<String>,
    orderHistoryResponseModel: MutableState<OrderHistoryResponseModel>,
){
    val refresh = remember { mutableStateOf(true) }
    val orderHistoryResponseModelLocal = remember { mutableStateOf(OrderHistoryResponseModel(orderHistory = arrayListOf())) }

    if (refresh.value){
        refresh.value = false
        OrderHistoryApi(
            protoViewModelLoginModel = protoViewModelLoginModel,
            progress = progress,
            orderHistoryResponseModel = orderHistoryResponseModelLocal,
            Symbol = symbol.value,
            isFailureOccurred = isFailureOccurred,
            valueUpdateStatus = object : ValueUpdateStatus {
                override fun valueUpdateSuccessful() {
                    orderHistoryResponseModelLocal.value.orderHistory.sortByDescending {
                        it.dateTime = getDateTimeChangeFormat(it.time)
                        it.time
                    }
                    orderHistoryResponseModel.value = orderHistoryResponseModelLocal.value
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
        if (orderHistoryResponseModel.value.orderHistory.size > 0) {
            orderHistoryResponseModel.value.orderHistory.forEach { data ->
                Spacer(modifier = Modifier.padding(top = 5.dp))
//                TradeHistoryCardView(Data = data, refresh = refresh, profileName = profileName)
                Spacer(modifier = Modifier.padding(top = 5.dp))
            }
        }
            else {
            Text(text = "No Record Found" , color = Color.Gray)
            }

        }
    }

}
