package com.example.tradingbot.presentation.home

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.model.ProfileModel.ProfileResponseModel
import com.example.tradingbot.domain.use_cases.restapi.*
import com.example.tradingbot.presentation.app.MainTopBar
import com.example.tradingbot.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val protoViewModelLoginModel: ProtoViewModelLoginModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TradingBotTheme {
                val profileData = remember { mutableStateOf(ProfileResponseModel(email = "",first_name="",last_name="",id=0,image_url="")) }
                val isExpanded = remember { mutableStateOf(true) }

                val progress = remember { mutableStateOf(false) }
                val isLogoutCalled = remember { mutableStateOf(false) }
                val isFailureOccurred = remember { mutableStateOf(false) }
                val profileName = remember { mutableStateOf(profileData.value.first_name) }
                Column(verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
                    .fillMaxSize(1f)) {
                    if (isExpanded.value){
                        isExpanded.value = false
                        ProfileApi(
                            protoViewModelLoginModel = protoViewModelLoginModel,
                            progress = progress,
                            profileResponseModel = profileData,
                            isFailureOccurred = isFailureOccurred,
                            valueUpdateStatus = object : ValueUpdateStatus{
                                override fun valueUpdateSuccessful() {
                                    profileName.value = profileData.value.first_name
                                }

                                override fun valueUpdateFailure() {
                                    isFailureOccurred.value = true
                                }

                            }
                        )
                    }

                    Text(text = "Hi")

                    Text(text = profileName.value)




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
