package com.example.tradingbot.presentation.home.screencomponents


import android.util.Log
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
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem
import com.example.tradingbot.domain.use_cases.restapi.AccountInfoApi
import com.example.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.example.tradingbot.presentation.home.components.AccountInFoCardView


@Composable
fun HomeScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
){

    val refresh = remember { mutableStateOf(true) }
    val accountInfoResponseModel = remember { mutableListOf<AccountInfoResponseModelItem>() }
    val valueUpdate = remember { mutableStateOf(false) }
    val tradeHistoryButton = remember { mutableStateOf(false) }
    val orderHistoryButton = remember { mutableStateOf(false) }

    if (refresh.value){
        refresh.value = false
        AccountInfoApi(
            protoViewModelLoginModel = protoViewModelLoginModel,
            progress = progress,
            accountInfoResponseModel = accountInfoResponseModel,
            isFailureOccurred = isFailureOccurred,
            valueUpdateStatus = object : ValueUpdateStatus{
                override fun valueUpdateSuccessful() {
                    accountInfoResponseModel.sortByDescending {
                        it.walletBalance
                    }
                    valueUpdate.value = true
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
        .fillMaxHeight(0.9f)
        .verticalScroll(rememberScrollState())
    )
    {
        if (valueUpdate.value){
            accountInfoResponseModel.forEach { data ->
                AccountInFoCardView(Data = data, refresh = refresh)
                Spacer(modifier = Modifier.padding(top= 10.dp))
            }
        }
    }
}