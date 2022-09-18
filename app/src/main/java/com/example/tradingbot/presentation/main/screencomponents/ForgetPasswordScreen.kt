package com.example.tradingbot.presentation.main.screencomponents

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.use_cases.restapi.ForgetPasswordApi
import com.example.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.example.tradingbot.presentation.main.components.InputField
import com.example.tradingbot.presentation.main.components.ScreenShiftButton
import com.example.tradingbot.ui.theme.*

@Composable
fun ForgetPasswordScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred: MutableState<Boolean>,
    isSignInPageOpen: MutableState<Boolean>,
    isForgetPasswordPageOpen: MutableState<Boolean>,
    isResetPasswordPageOpen: MutableState<Boolean>,
) {
    val email = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf("") }
    val isVerifyEmailCalled = remember { mutableStateOf(false) }
    val fakeState = remember { mutableStateOf(false) }

    if (isVerifyEmailCalled.value) {
        isVerifyEmailCalled.value = false
        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailError.value = "Invalid Email"
        } else {
            emailError.value = ""
            ForgetPasswordApi(
                protoViewModelLoginModel = protoViewModelLoginModel,
                progress = progress,
                email = email.value,
                isFailureOccurred = isFailureOccurred,
                valueUpdateStatus = object : ValueUpdateStatus {
                    override fun valueUpdateSuccessful() {
                        isForgetPasswordPageOpen.value = false
                        isResetPasswordPageOpen.value = true
                    }

                    override fun valueUpdateFailure() {
                        isFailureOccurred.value = true
                    }
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
    Card(
        elevation = 10.dp,

        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White, shape = RoundedCornerShape(50.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.reset_password),
                contentDescription = app_logo,
                Modifier
                    .size(50.dp)
                    .padding(top = 10.dp),
                Alignment.Center,
            )
            Row(
                horizontalArrangement = Arrangement.Center,

                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(30.dp)
            ) {
                Text(text = Forget_your_Passwor, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
            Row(
                horizontalArrangement = Arrangement.Center,

                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(30.dp)
            ) {
                Text(text = Forget_your_Passwor_Disc, color = Color.Gray)
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp)
            )

            InputField(label = email_address, mutableState = email, isPassword = false)
            if (emailError.value.isNotEmpty()) {
                Text(text = emailError.value, color = Red)
            }

            Spacer(modifier = Modifier.padding(top = 30.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),

                verticalAlignment = Alignment.CenterVertically,
            ) {
                ScreenShiftButton(
                    fromScreen = isForgetPasswordPageOpen,
                    toScreen = isSignInPageOpen,
                    text = back

                )
                ScreenShiftButton(
                    fromScreen = fakeState,
                    toScreen = isVerifyEmailCalled,
                    text = send_otp

                )

            }


        }

    }
}
}