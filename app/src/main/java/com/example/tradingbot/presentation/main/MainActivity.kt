package com.example.tradingbot.presentation.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.use_cases.restapi.CustomProgressBar
import com.example.tradingbot.domain.use_cases.restapi.CustomWarningAlertWithRetry
import com.example.tradingbot.domain.use_cases.restapi.CustomWarningAlertWithTwoButtons
import com.example.tradingbot.domain.use_cases.restapi.WarningsCallBack
import com.example.tradingbot.presentation.app.MainTopBar
import com.example.tradingbot.presentation.main.screencomponents.LandingPage
import com.example.tradingbot.presentation.main.screencomponents.SignInPage
import com.example.tradingbot.presentation.main.screencomponents.Signup
import com.example.tradingbot.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val protoViewModelLoginModel: ProtoViewModelLoginModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TradingBotTheme {
                val context = LocalContext.current as ComponentActivity

                val isLandingPageOpen = remember { mutableStateOf(true) }
                val isSignInPageOpen = remember { mutableStateOf(false) }
                val isSignUpPageOpen = remember { mutableStateOf(false) }
                val isForgetPasswordPageOpen = remember { mutableStateOf(false) }

                val isAppPowerOffCalled = remember { mutableStateOf(false) }

                val progress = remember { mutableStateOf(false) }
                val isFailureOccurred = remember { mutableStateOf(false) }

                Column(modifier = Modifier
                    .fillMaxSize(1f)
                    .background(gradientGrayblack)) {

                    if (!isLandingPageOpen.value) {
                        MainTopBar(iconExpanded = isAppPowerOffCalled)
                    }
                    if (isLandingPageOpen.value){
                        LandingPage(isLandingPageOpen = isLandingPageOpen, isSignInPageOpen = isSignInPageOpen)
                    }
                    else if(isSignInPageOpen.value){
                        SignInPage(
                            protoViewModelLoginModel = protoViewModelLoginModel,
                            progress = progress,
                            isFailureOccurred = isFailureOccurred,
                            isLandingPageOpen = isLandingPageOpen,
                            isSignInPageOpen = isSignInPageOpen,
                            isSignUpPageOpen = isSignUpPageOpen,
                            isForgetPasswordPageOpen = isForgetPasswordPageOpen,

                        )

                    }else if(isSignUpPageOpen.value){
                        Signup(isLandingPageOpen = isLandingPageOpen, isSignInOpen = isSignInPageOpen, isSignUpOpen = isSignUpPageOpen)

                    }

                    
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
                if (isAppPowerOffCalled.value){
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
                                isAppPowerOffCalled.value = false
                            }
                        }
                    )

                }

            }
        }
    }
}

