package com.example.tradingbot.presentation.app

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Switch
import com.example.tradingbot.ui.theme.Red

@Composable
fun BotStatus(currentStatus : MutableState<Boolean>, statusChanged : MutableState<Boolean>) {
    Row() {
//        Text(text = "Bot Status", color = Color.Black)
        androidx.compose.material.Switch(
            checked = currentStatus.value,
            onCheckedChange = { statusChanged.value = true },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Red,
                checkedTrackColor = Color.Green,
                uncheckedTrackColor = Color.Red,
            )

        )
    }
    

}