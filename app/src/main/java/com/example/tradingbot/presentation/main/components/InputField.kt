package com.example.tradingbot.presentation.main.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.tradingbot.ui.theme.pass

@Composable
fun InputField(label: String, mutableState: MutableState<String>, isPassword : Boolean) {
    OutlinedTextField(
        value = mutableState.value,
        label = { Text(text = label) },
        onValueChange = { mutableState.value = it },
//        placeholder = { Text("${label}", color = Color.White) },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            textColor = Color.Black,
        ), visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    )
}