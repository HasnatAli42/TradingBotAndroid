package com.smart.tradingbot.presentation.main.screencomponents

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smart.tradingbot.presentation.main.components.ScreenShiftButton
import com.smart.tradingbot.ui.theme.*
import com.smart.tradingbot.R
import com.smart.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.smart.tradingbot.domain.use_cases.restapi.SignUpApi
import com.smart.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.smart.tradingbot.domain.validations.SignUpCredentialsValidations
import com.smart.tradingbot.presentation.home.HomeActivity
import com.smart.tradingbot.presentation.main.components.InputFieldWithError


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Signup(
    isSignInOpen: MutableState<Boolean>,
    isSignUpOpen: MutableState<Boolean>,
    protoViewModelLoginModel : ProtoViewModelLoginModel,
    progress : MutableState<Boolean>,
    isFailureOccurred : MutableState<Boolean>,
) {
    val context = LocalContext.current as ComponentActivity
    val intent = Intent(context, HomeActivity::class.java)
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val apiKey = remember { mutableStateOf("") }
    val secretKey = remember { mutableStateOf("") }

    val firstNameError = remember { mutableStateOf("") }
    val lastNameError = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf("") }
    val passwordError = remember { mutableStateOf("") }
    val confirmPasswordError = remember { mutableStateOf("") }
    val apiKeyError = remember { mutableStateOf("") }
    val secretKeyError = remember { mutableStateOf("") }
    val termsError = remember { mutableStateOf("") }


    val scrollState = rememberScrollState()
    val terms = remember { mutableStateOf(false) }
    val fakeState = remember { mutableStateOf(false) }
    val isSignUpCalled = remember { mutableStateOf(false) }

     if(isSignUpCalled.value){
         isSignUpCalled.value = false
    if (SignUpCredentialsValidations(firstName = firstName.value, firstNameError = firstNameError,
            lastName = lastName.value, lastNameError = lastNameError,
            email = email.value, emailError = emailError,
            setPassword = password.value, setPasswordError = passwordError,
            confirmPassword = confirmPassword.value, confirmPasswordError = confirmPasswordError,
            apiKey = apiKey.value, apiKeyError = apiKeyError,
            secretKey = secretKey.value, secretKeyError = secretKeyError,
            termsAgreed = terms.value, termsAgreedError = termsError,
        )){
        SignUpApi(
            protoViewModelLoginModel = protoViewModelLoginModel,
            progress = progress,
            email = email.value,
            password = password.value,
            password2 = confirmPassword.value,
            firstName = firstName.value,
            lastName = lastName.value,
            api_Key = apiKey.value,
            secret_key = secretKey.value,
            isFailureOccurred = isFailureOccurred,
            intent = intent,
            valueUpdateStatus = object : ValueUpdateStatus{
                override fun valueUpdateSuccessful() {
                    context.finish()
                    context!!.startActivity(intent)
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
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .verticalScroll(scrollState)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.signup),
                    contentDescription = sign_up,
                    Modifier.size(50.dp)
                )

                Text(text = sign_up, fontSize = 30.sp, color = Color.Black)
                Spacer(modifier = Modifier.padding(top = 10.dp))

                InputFieldWithError(first_name, firstName,firstNameError,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(last_name, lastName,lastNameError,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(email_address, email,emailError,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(pass, password, passwordError,true)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(ConfirmPass, confirmPassword, confirmPasswordError,true)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(Api_key, apiKey, apiKeyError,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputFieldWithError(Secret_key, secretKey, secretKeyError,false)


                Row(
                    horizontalArrangement = Arrangement.Center,

                    modifier = Modifier
                        .fillMaxWidth(1f)
//                        .background(gradientGrayblack)
                        .padding(30.dp)
                ) {

//                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        Checkbox(
                            checked = terms.value,
                            onCheckedChange = { terms.value = it },
                            modifier = Modifier.padding(5.dp),
                            colors = CheckboxDefaults.colors(Color.DarkGray)
                        )
                        Text(
                            text = terms_and_conditions,
                            modifier = Modifier.padding(5.dp), color = Color.LightGray
                        )
//                    }

                }
                if(termsError.value.isNotEmpty()){
                    Text(
                        text = termsError.value, color = Red
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,

                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(20.dp),

                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ScreenShiftButton(
                        fromScreen = isSignUpOpen,
                        toScreen = isSignInOpen,
                        text = back
                    )
                    ScreenShiftButton(
                        fromScreen = fakeState,
                        toScreen = isSignUpCalled,
                        text = sign_up
                    )
                }
            }
        }
    }
}