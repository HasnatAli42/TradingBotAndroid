package com.example.tradingbot.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tradingbot.ui.theme.sign_up

@Composable
fun LandingLayout(painterid: Int, text1: String, text2: String) {
    Row(
        modifier = Modifier.fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = painterid),
            contentDescription = sign_up,
            modifier = Modifier
                .size(60.dp)
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(1f)

        ) {
            Text(text = text1, color = Color.White)
            Spacer(modifier = Modifier.padding(5.dp))

            Text(text = text2, color = Color.White)
        }
    }
}