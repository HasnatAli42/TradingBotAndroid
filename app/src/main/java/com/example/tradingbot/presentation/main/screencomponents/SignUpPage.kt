package com.example.tradingbot.presentation.main.screencomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.presentation.main.components.InputField
import com.example.tradingbot.presentation.main.components.ScreenShiftButton
import com.example.tradingbot.ui.theme.*
import com.example.tradingbot.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Signup(
    isLandingPageOpen: MutableState<Boolean>,
    isSignInOpen: MutableState<Boolean>,
    isSignUpOpen: MutableState<Boolean>
) {
    val user_first_name = remember { mutableStateOf("") }
    val user_last_name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val api_key = remember { mutableStateOf("") }
    val secret_key = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val terms = remember {
        mutableStateOf(false)
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

                InputField(first_name, user_first_name,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputField(last_name, user_last_name,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputField(email_address, email,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputField(pass, password,true)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputField(pass, confirmPassword,true)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputField(Api_key, api_key,false)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputField(Secret_key, secret_key,false)


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
                        fromScreen = isSignUpOpen,
                        toScreen = isSignInOpen,
                        text = sign_up
                    )
                }
            }
        }
    }
}