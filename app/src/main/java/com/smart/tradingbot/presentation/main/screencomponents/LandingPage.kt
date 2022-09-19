package com.smart.tradingbot.presentation.main.screencomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smart.tradingbot.presentation.main.components.LandingLayout
import com.smart.tradingbot.ui.theme.*

@Composable
fun LandingPage(isLandingPageOpen: MutableState<Boolean>, isSignInPageOpen: MutableState<Boolean>) {
    val scrollState = rememberScrollState()

     Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.9f)
            .background(gradientGrayblack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .padding(20.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = slogn,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = lightGreen,
                textAlign = TextAlign.Center,
            )
            Text(
                text = slogn2, fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
            )

            Divider(color = Color.White, thickness = 5.dp)
            Button(
                onClick = {
                    isLandingPageOpen.value = false
                    isSignInPageOpen.value = true
                },
                modifier = Modifier.size(width = 250.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = bluecolor,
                    contentColor = Color.White
                ),
            ) {

                Text(text = get_started_button)
            }
            LandingLayout(
                painterid = R.drawable.signup_dashboard,
                text1 = SignUpText,
                text2 = register
            )
            LandingLayout(
                painterid = R.drawable.connect_binance,
                text1 = connect_binance,
                text2 = connect_binance_desc
            )
            LandingLayout(
                painterid = R.drawable.graph_logo,
                text1 = automated_trading,
                text2 = automated_trading_desc
            )

        }

    }


}