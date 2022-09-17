package com.example.tradingbot.presentation.home.screencomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.functions.time.getDateTime
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModel
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModelItem
import com.example.tradingbot.domain.use_cases.restapi.AccountInfoApi
import com.example.tradingbot.domain.use_cases.restapi.TradeHistoryApi
import com.example.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.example.tradingbot.presentation.home.components.AccountInFoCardView
import com.example.tradingbot.presentation.home.components.TradeHistoryCardView

@Composable
fun TradeHistoryScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
    profileName : MutableState<String>,
    tradeHistoryResponseModel: MutableState<TradeHistoryResponseModel>,
){
    val refresh = remember { mutableStateOf(true) }
    val tradeHistoryResponseModelLocal = remember { mutableStateOf(TradeHistoryResponseModel(tradeHistory = arrayListOf())) }

    if (refresh.value){
        refresh.value = false
        TradeHistoryApi(
            protoViewModelLoginModel = protoViewModelLoginModel,
            progress = progress,
            tradeHistoryResponseModel = tradeHistoryResponseModelLocal,
            isFailureOccurred = isFailureOccurred,
            valueUpdateStatus = object : ValueUpdateStatus {
                override fun valueUpdateSuccessful() {
                    tradeHistoryResponseModelLocal.value.tradeHistory.sortByDescending {
                        it.dateTime = getDateTime(it.time)
                        it.time
                    }
                    tradeHistoryResponseModel.value = tradeHistoryResponseModelLocal.value
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
            tradeHistoryResponseModel.value.tradeHistory.forEach { data ->
                Spacer(modifier = Modifier.padding(top= 5.dp))
                TradeHistoryCardView(Data = data, refresh = refresh, profileName = profileName)
                Spacer(modifier = Modifier.padding(top= 5.dp))
        }
    }

}
