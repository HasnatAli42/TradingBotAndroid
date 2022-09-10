package com.example.tradingbot.presentation.main.screencomponents


import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext
import com.example.tradingbot.common.protoDataStore.ProtoViewModelLoginModel
import com.example.tradingbot.domain.functions.callbacks.ValueUpdateStatusLogin
import com.example.tradingbot.domain.model.LoginModel.LoginResponseModel
import com.example.tradingbot.domain.use_cases.restapi.LoginApi
import com.example.tradingbot.domain.use_cases.restapi.ValueUpdateStatus
import com.example.tradingbot.domain.use_cases.restapi.displayErrorMsg
import com.example.tradingbot.presentation.home.HomeActivity
import com.example.tradingbot.presentation.main.components.InputField
import com.example.tradingbot.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignInPage(
    protoViewModelLoginModel: ProtoViewModelLoginModel,
    progress: MutableState<Boolean>,
    isFailureOccurred: MutableState<Boolean>,
    isLandingPageOpen: MutableState<Boolean>,
    isSignInPageOpen: MutableState<Boolean>,
    isSignUpPageOpen: MutableState<Boolean>,
    isForgetPasswordPageOpen: MutableState<Boolean>,

    ) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val isLoginCalled = remember { mutableStateOf(false) }
    val rememberMe = remember {
        mutableStateOf(false)
    }
    val increment = remember { mutableStateOf(1) }
    val context = LocalContext.current as ComponentActivity
    val intent = Intent(context, HomeActivity::class.java)
    val message = remember { mutableStateOf("") }


    protoViewModelLoginModel.tokenLiveData.observe(context) {
        if (it.isRemembered && increment.value == 1) {
            if (it.emailAddress.isNotEmpty() && it.password.isNotEmpty()) {
                email.value = it.emailAddress
                password.value = it.password
                rememberMe.value = it.isRemembered
                increment.value = 2
            }
        }
    }

    if (isLoginCalled.value){
        isLoginCalled.value = false
        LoginApi(
            protoViewModelLoginModel =protoViewModelLoginModel ,
            progress = progress,
            email = email.value,
            password = password.value,
            rememberMe = rememberMe.value,
            isFailureOccurred = isFailureOccurred,
            intent = intent,
            message = message,
            valueUpdateStatus = object : ValueUpdateStatus {
                override fun valueUpdateSuccessful() {
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.finish()
                    context!!.startActivity(intent)
                }

                override fun valueUpdateFailure() {
                    isFailureOccurred.value = true
                }

            }
        )
    }
    
    

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(20.dp)

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .verticalScroll(scrollState)
                    .background(White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp, end = 15.dp))

                Image(
                    painter = painterResource(id = R.drawable.padlock),
                    contentDescription = padlock,
                    Modifier.size(35.dp)
                )
                Text(text = sign_in, fontSize = 30.sp, color = Black)
                Spacer(modifier = Modifier.padding(top = 35.dp))
                InputField(label = email_address, mutableState = email, isPassword = false)
                Spacer(modifier = Modifier.padding(top = 20.dp))
                InputField(label = pass, mutableState = password , isPassword = true)
                if (isError.value) {
                    Text(text = validity_check, color = Color.Red)
                }

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,

                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(30.dp)
                ) {

//                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        Checkbox(
                            checked = rememberMe.value,
                            onCheckedChange = { rememberMe.value = it },
                            modifier = Modifier.padding(3.dp),
                            colors = CheckboxDefaults.colors(DarkGray)
                        )
                        Text(
                            text = remember_password,
                            modifier = Modifier.padding(5.dp),
                            color = Color.Gray
                        )
//                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,

                    modifier = Modifier
                        .fillMaxWidth(1f)


                ) {
                    Text(
                        text = forgot_passwrod,
                        modifier = Modifier.clickable {
                            isSignInPageOpen.value = false
                            isForgetPasswordPageOpen.value = true
                        },
                        color = Color.Black

                    )

                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,

                    modifier = Modifier
                        .fillMaxWidth(1f)


                ) {

                    Text(
                        text = no_account, modifier = Modifier.clickable {
                            isSignInPageOpen.value = false
                            isSignUpPageOpen.value = true
                        },
                        color = Color.Black
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,

                    modifier = Modifier
                        .fillMaxWidth(1f)

                        .padding(20.dp),

                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                            isLandingPageOpen.value = true
                            isSignInPageOpen.value = false

                        },

                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = bluecolor,
                            contentColor = Color.White
                        ),

                        ) {
                        Text(text = back)
                    }
                    Button(
                        onClick = {
                            if (Patterns.EMAIL_ADDRESS.matcher(email.value)
                                    .matches() && password.value.length > 6
                            ) {
                                isError.value = false
                                isLoginCalled.value = true
                            } else {
                                isError.value = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = bluecolor,
                            contentColor = Color.White
                        ),

                        ) {
                        Text(text = sign_in)
                    }
                }

            }

        }
    }
}






