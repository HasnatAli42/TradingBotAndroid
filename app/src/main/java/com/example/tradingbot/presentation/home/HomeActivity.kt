package com.example.tradingbot.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModel
import com.example.tradingbot.domain.model.AccountInfoModel.AccountInfoResponseModelItem
import com.example.tradingbot.domain.model.OpenOrdersResponseModel.OpenOrderResponseModel
import com.example.tradingbot.domain.model.OpenTradesResponseModel.OpenTradesResponseModel
import com.example.tradingbot.domain.model.OrderHistoryResponseModel.OrderHistoryResponseModel
import com.example.tradingbot.domain.model.ProfileModel.ProfileResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModel
import com.example.tradingbot.domain.model.TradeHistoryModel.TradeHistoryResponseModelItem
import com.example.tradingbot.domain.use_cases.restapi.*
import com.example.tradingbot.presentation.app.HomeBottomBar
import com.example.tradingbot.presentation.app.HomeTopBar
import com.example.tradingbot.presentation.home.screencomponents.*
import com.example.tradingbot.presentation.main.MainActivity
import com.example.tradingbot.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val protoViewModelLoginModel: ProtoViewModelLoginModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TradingBotHomeTheme {
                val context = LocalContext.current as ComponentActivity
                val profileData = remember { mutableStateOf(ProfileResponseModel(email = "",first_name="",last_name="",id=0,drive_image_url="")) }
                val isInitiated = remember { mutableStateOf(true) }

                val progress = remember { mutableStateOf(false) }
                val isLogoutCalled = remember { mutableStateOf(false) }
                val isFailureOccurred = remember { mutableStateOf(false) }
                val profileName = remember { mutableStateOf(profileData.value.first_name) }
                val profileImg = remember { mutableStateOf(profileData.value.drive_image_url) }
                val isPopUpMenu = remember { mutableStateOf(false) }

//                TabsButtonStart
                val homeButton = remember { mutableStateOf(true) }
                val orderButton = remember { mutableStateOf(false) }
                val positionButton = remember { mutableStateOf(false) }
                val tradeHistoryButton = remember { mutableStateOf(false) }
                val orderHistoryButton = remember { mutableStateOf(false) }
//                TabsButtonEnd

//                Data Providers
                val accountInfoResponseModel = remember { mutableStateOf(AccountInfoResponseModel(accountInfo = arrayListOf())) }
                val tradeHistoryResponseModel = remember { mutableStateOf(TradeHistoryResponseModel(tradeHistory = arrayListOf())) }
                val orderHistoryResponseModel = remember { mutableStateOf(OrderHistoryResponseModel(orderHistory = arrayListOf())) }
                val openOrdersResponseModel = remember { mutableStateOf(OpenOrderResponseModel(openOrders = arrayListOf())) }
                val openTradesResponseModel = remember { mutableStateOf(OpenTradesResponseModel(openTrades = arrayListOf())) }

                if (isInitiated.value){
                    isInitiated.value = false
                    ProfileApi(
                        protoViewModelLoginModel = protoViewModelLoginModel,
                        progress = progress,
                        profileResponseModel = profileData,
                        isFailureOccurred = isFailureOccurred,
                        valueUpdateStatus = object : ValueUpdateStatus{
                            override fun valueUpdateSuccessful() {
                                profileName.value = profileData.value.first_name
                                profileImg.value = profileData.value.drive_image_url
                                displayErrorMsg(context, "Welcome ${profileData.value.first_name} ${profileData.value.last_name}")
                            }

                            override fun valueUpdateFailure() {
                                isFailureOccurred.value = true
                            }
                        }
                    )
                }


                Column(verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
                    .fillMaxSize(1f)) {

                    HomeTopBar(profileName = profileName, profileImage = profileImg, profilePicClickState = isPopUpMenu)

                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight(0.88f)
                            .fillMaxWidth(1f)) {
                        if (homeButton.value){
                            HomeScreen(
                                protoViewModelLoginModel = protoViewModelLoginModel,
                                progress = progress,
                                isFailureOccurred = isFailureOccurred,
                                profileName = profileName,
                                accountInfoResponseModel = accountInfoResponseModel,
                            )
                        }else if (orderButton.value){
                            OpenOrdersScreen(
                                protoViewModelLoginModel = protoViewModelLoginModel,
                                progress = progress,
                                isFailureOccurred = isFailureOccurred,
                                openOrdersResponseModel = openOrdersResponseModel
                            )
                        }else if (positionButton.value){
                            OpenTradesScreen(
                                protoViewModelLoginModel = protoViewModelLoginModel,
                                progress = progress,
                                isFailureOccurred = isFailureOccurred,
                                openTradesResponseModel = openTradesResponseModel
                            )
                        }else if (tradeHistoryButton.value){
                            TradeHistoryScreen(
                                protoViewModelLoginModel = protoViewModelLoginModel,
                                progress = progress,
                                isFailureOccurred = isFailureOccurred,
                                profileName = profileName,
                                tradeHistoryResponseModel = tradeHistoryResponseModel
                            )
                        }else if (orderHistoryButton.value) {
                            OrderHistoryScreen(
                                protoViewModelLoginModel = protoViewModelLoginModel,
                                progress = progress,
                                isFailureOccurred = isFailureOccurred,
                                orderHistoryResponseModel = orderHistoryResponseModel
                            )
                        }
                    }


                    HomeBottomBar(
                        homeButton = homeButton,
                        orderButton = orderButton,
                        positionButton = positionButton,
                        tradeHistoryButton = tradeHistoryButton,
                        orderHistoryButton = orderHistoryButton
                    )




                }
                if (progress.value) {
                    CustomProgressBar()
                }
                if (isFailureOccurred.value) {
                    CustomWarningAlertWithRetry(
                        message = internetError,
                        color = PopUpHomeScreen,
                        warningsCallBack = object : WarningsCallBack {
                            override fun onRetry() {
//                                increment.value = 1
                                isFailureOccurred.value = false
                            }

                            override fun onCancel() {
                            }
                        }
                    )
                }
                if (isLogoutCalled.value){
                    val intent = Intent(context, MainActivity::class.java)
                    context.finish()
                    context!!.startActivity(intent)


                }
                ProfilePopUp(isPopUpState = isPopUpMenu, isLogoutCalled= isLogoutCalled)

                }
            }
        }
    }
