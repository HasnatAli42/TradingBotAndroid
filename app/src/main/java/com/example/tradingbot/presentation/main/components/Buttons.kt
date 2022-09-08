package com.example.tradingbot.presentation.main.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.tradingbot.ui.theme.bluecolor

@Composable
fun ScreenShiftButton(
    fromScreen: MutableState<Boolean>,
    toScreen: MutableState<Boolean>,
    text: String
) {
    Button(
        onClick = {
            toScreen.value = true
            fromScreen.value = false

        },

        colors = ButtonDefaults.buttonColors(
            backgroundColor = bluecolor,
            contentColor = Color.White
        ),

        ) {
        Text(text = text)
    }
}