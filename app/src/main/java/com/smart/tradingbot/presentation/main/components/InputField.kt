package com.smart.tradingbot.presentation.main.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.smart.tradingbot.ui.theme.Red

@Composable
fun InputField(label: String, mutableState: MutableState<String>, isPassword : Boolean) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
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

@Composable
fun InputFieldWithError(label: String, mutableState: MutableState<String>,mutableStateError: MutableState<String>, isPassword : Boolean) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
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
    if (mutableStateError.value.isNotEmpty()){
        Text(text = mutableStateError.value, color = Red)
    }
}