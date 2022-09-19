package com.smart.tradingbot.presentation.main.screencomponents

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
import com.smart.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.smart.tradingbot.domain.use_cases.restapi.ResetPasswordApi
import com.smart.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.smart.tradingbot.domain.validations.OtpFieldsValidation
import com.smart.tradingbot.presentation.main.components.InputFieldWithError
import com.smart.tradingbot.presentation.main.components.ScreenShiftButton
import com.smart.tradingbot.ui.theme.*

@Composable
fun ResetPasswordScreen(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred: MutableState<Boolean>,
    isSignInPageOpen: MutableState<Boolean>,
    isForgetPasswordPageOpen: MutableState<Boolean>,
    isResetPasswordPageOpen: MutableState<Boolean>,
) {
    val otp = remember { mutableStateOf("") }
    val otpError = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordError = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val confirmPasswordError = remember { mutableStateOf("") }
    val isResetPasswordCalled = remember { mutableStateOf(false) }
    val fakeState = remember { mutableStateOf(false) }

    if (isResetPasswordCalled.value) {
        isResetPasswordCalled.value = false
        if (OtpFieldsValidation(
                otp = otp.value, otpError = otpError,
                setPassword = password.value, setPasswordError = passwordError,
                confirmPassword = confirmPassword.value, confirmPasswordError = confirmPasswordError,
        )) {
            ResetPasswordApi(
                protoViewModelLoginModel = protoViewModelLoginModel,
                progress = progress,
                otp = otp.value,
                password = password.value,
                password2 = confirmPassword.value,
                isFailureOccurred = isFailureOccurred,
                valueUpdateStatus = object : ValueUpdateStatus {
                    override fun valueUpdateSuccessful() {
                        isResetPasswordPageOpen.value = false
                        isSignInPageOpen.value = true
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
                .background(Color.White, RoundedCornerShape(50.dp))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reset_password_with_otp),
                    contentDescription = pass_reset_icon,
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
                    Text(text = Verify_email, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,

                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(30.dp)
                ) {
                    Text(text = otp_description, color = Color.Gray)
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(10.dp)
                )

                InputFieldWithError(label =OTP, mutableState = otp, mutableStateError = otpError, isPassword = false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(label = new_pass, mutableState = password, mutableStateError = passwordError, isPassword = true)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(label = pass_confirm, mutableState = confirmPassword, mutableStateError = confirmPasswordError, isPassword = true)
                Row(
                    horizontalArrangement = Arrangement.Center,

                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(30.dp)
                ) {
                    Text(text = otp_not_Received, color = Color.Gray)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),

                    verticalAlignment = Alignment.CenterVertically,) {
                    ScreenShiftButton(
                        fromScreen = isResetPasswordCalled,
                        toScreen = isForgetPasswordPageOpen,
                        text = back

                    )
                    ScreenShiftButton(
                        fromScreen = fakeState,
                        toScreen = isResetPasswordCalled,
                        text = confirm

                    )

                }


            }

        }
    }
}