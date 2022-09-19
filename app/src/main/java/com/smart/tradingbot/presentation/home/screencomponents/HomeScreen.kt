package com.smart.tradingbot.presentation.home.screencomponents


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
import com.smart.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.smart.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModel
import com.smart.tradingbot.domain.use_cases.restapi.AccountInfoApi
import com.smart.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.smart.tradingbot.presentation.home.components.AccountInFoCardView


@Composable
fun HomeScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
    profileName : MutableState<String>,
    accountInfoResponseModel : MutableState<AccountInfoResponseModel>,
){

    val refresh = remember { mutableStateOf(true) }
    val tradeHistoryButton = remember { mutableStateOf(false) }
    val orderHistoryButton = remember { mutableStateOf(false) }
    val accountInfoResponseModelLocal = remember { mutableStateOf(AccountInfoResponseModel(accountInfo = arrayListOf())) }

    if (refresh.value){
        refresh.value = false
        AccountInfoApi(
            protoViewModelLoginModel = protoViewModelLoginModel,
            progress = progress,
            accountInfoResponseModel = accountInfoResponseModelLocal,
            isFailureOccurred = isFailureOccurred,
            valueUpdateStatus = object : ValueUpdateStatus{
                override fun valueUpdateSuccessful() {
                    accountInfoResponseModelLocal.value.accountInfo.sortByDescending {
                        it.walletBalance
                    }
                    accountInfoResponseModel.value = accountInfoResponseModelLocal.value
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
            accountInfoResponseModel.value.accountInfo.forEach { data ->
                Spacer(modifier = Modifier.padding(top= 5.dp))
                AccountInFoCardView(Data = data, refresh = refresh, profileName = profileName)
                Spacer(modifier = Modifier.padding(top= 5.dp))
            }
    }
}