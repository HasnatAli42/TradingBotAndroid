package com.example.tradingbot.presentation.app

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradingbot.R
import com.example.tradingbot.ui.theme.*

@Composable
fun MainTopBar(iconExpanded: MutableState<Boolean>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(gradientBlackGray)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 25.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
        ) {
                Image(
                    painter = painterResource(id = R.drawable.stock_market),
                    contentDescription = "",
                    Modifier.size(40.dp, 40.dp)
                )
                Text(text = tradingBot, fontFamily = RobotoCondensed, color = lightGreen, fontWeight = FontWeight.Bold, fontSize = 30.sp)

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_power_settings_new_24),
                contentDescription = "",
                Modifier.size(40.dp, 40.dp).clickable {
                    iconExpanded.value = true
                }
            )
        }
    }
}