package com.example.tradingbot.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.use_cases.restapi.CustomProgressBar
import com.example.tradingbot.domain.use_cases.restapi.CustomWarningAlertWithRetry
import com.example.tradingbot.domain.use_cases.restapi.WarningsCallBack
import com.example.tradingbot.domain.use_cases.restapi.displayErrorMsg
import com.example.tradingbot.presentation.app.TopBar
import com.example.tradingbot.presentation.main.screencomponents.LandingPage
import com.example.tradingbot.presentation.main.screencomponents.SignInPage
import com.example.tradingbot.presentation.main.screencomponents.Signup
import com.example.tradingbot.ui.theme.TradingBotTheme
import com.example.tradingbot.ui.theme.gradientGrayblack
import com.example.tradingbot.ui.theme.internetError
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

                val progress = remember { mutableStateOf(false) }
                val isFailureOccurred = remember { mutableStateOf(false) }

                Column(modifier = Modifier
                    .fillMaxSize(1f)
                    .background(gradientGrayblack)) {

                    if (!isLandingPageOpen.value) {
                        TopBar(iconExpanded = isForgetPasswordPageOpen)
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

            }
        }
    }
}

