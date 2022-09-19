package com.smart.tradingbot.presentation.home.screencomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smart.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.smart.tradingbot.data.dataprovider.Symbol_List
import com.smart.tradingbot.domain.functions.time.getDateTimeChangeFormat
import com.smart.tradingbot.domain.model.OrderHistoryResponseModel.OrderHistoryResponseModel
import com.smart.tradingbot.domain.use_cases.restapi.OrderHistoryApi
import com.smart.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.smart.tradingbot.presentation.home.components.OrderHistoryCardView
import com.smart.tradingbot.presentation.main.components.SetMutableFalseButton
import com.smart.tradingbot.ui.theme.bluecolor

@Composable
fun OrderHistoryScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
    orderHistoryResponseModel: MutableState<OrderHistoryResponseModel>,
){
    val isApiCalled = remember { mutableStateOf(false) }
    val isApiCalledFirstTime = remember { mutableStateOf(false) }
    val symbol = remember { mutableStateOf("") }
    val orderHistoryResponseModelLocal = remember { mutableStateOf(OrderHistoryResponseModel(orderHistory = arrayListOf())) }


    if (isApiCalled.value && isApiCalledFirstTime.value){
        isApiCalledFirstTime.value = false
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
        if (isApiCalled.value){
            if (orderHistoryResponseModel.value.orderHistory.size > 0 && !progress.value) {
                orderHistoryResponseModel.value.orderHistory.forEach { data ->
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    OrderHistoryCardView(Data = data)
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                SetMutableFalseButton(mutableState = isApiCalled, text = "Go Back")
            }
            else {
                if (!progress.value){
                    Text(text = "No Record Found" , color = Color.Gray)
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    SetMutableFalseButton(mutableState = isApiCalled, text = "Go Back")
                }
            }
        }else{

            Symbol_List.forEach { current_symbol ->

                Button(
                    onClick = {
                        symbol.value = current_symbol
                        isApiCalled.value = true
                        isApiCalledFirstTime.value = true

                    },

                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = bluecolor,
                        contentColor = Color.White
                    ),

                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp, top = 5.dp, bottom = 5.dp)) {
                    Text(text = current_symbol)
                }
            }


        }


        }
    }


