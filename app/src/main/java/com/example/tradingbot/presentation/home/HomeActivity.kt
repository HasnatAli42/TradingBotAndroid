package com.example.tradingbot.presentation.home

import android.app.Activity
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
import com.example.tradingbot.domain.model.ProfileModel.ProfileResponseModel
import com.example.tradingbot.domain.use_cases.restapi.*
import com.example.tradingbot.presentation.app.HomeBottomBar
import com.example.tradingbot.presentation.app.HomeTopBar
import com.example.tradingbot.presentation.home.screencomponents.HomeScreen
import com.example.tradingbot.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val protoViewModelLoginModel: ProtoViewModelLoginModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TradingBotHomeTheme {
                val profileData = remember { mutableStateOf(ProfileResponseModel(email = "",first_name="",last_name="",id=0,image_url="")) }
                val isInitiated = remember { mutableStateOf(true) }

                val progress = remember { mutableStateOf(false) }
                val isLogoutCalled = remember { mutableStateOf(false) }
                val isFailureOccurred = remember { mutableStateOf(false) }
                val profileName = remember { mutableStateOf(profileData.value.first_name) }
                val profileImg = remember { mutableStateOf(profileData.value.image_url) }

//                TabsButtonStart
                val homeButton = remember { mutableStateOf(true) }
                val orderButton = remember { mutableStateOf(false) }
                val positionButton = remember { mutableStateOf(false) }
                val tradeHistoryButton = remember { mutableStateOf(false) }
                val orderHistoryButton = remember { mutableStateOf(false) }
//                TabsButtonEnd

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
                                profileImg.value = profileData.value.image_url
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

                    HomeTopBar(profileName = profileName, profileImage = profileImg)

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
                            )
                        }else if (orderButton.value){
                            Text(text = "Order Screen")
                        }else if (positionButton.value){
                            Text(text = "Position Screen")
                        }else if (tradeHistoryButton.value){
                            Text(text = "Trade History Screen")
                        }else if (orderHistoryButton.value) {
                            Text(text = "Order History Screen")
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
                        color = Color.LightGray,
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
                    val activity = (LocalContext.current as? Activity)
                    CustomWarningAlertWithTwoButtons(
                        firstButtonText= no,
                        secondButtonText= yes,
                        message = powerOff,
                        color = Color.LightGray,
                        warningsCallBack = object : WarningsCallBack {
                            override fun onRetry() {
                                activity?.finish()
                                isFailureOccurred.value = false
                            }

                            override fun onCancel() {
                                isLogoutCalled.value = false
                            }
                        }
                    )

                }

                }
            }
        }
    }
