package com.example.tradingbot.domain.validations

import android.util.Patterns
import androidx.compose.runtime.MutableState
import org.chromium.base.Log

fun loginCredentialsValidations(email: String, password: String, emailError: MutableState<String>,
                                passwordError: MutableState<String>
): Boolean {
    var isValid = true
    val capitalLetters = "[A-Z]".toRegex()
    val numbers = "[0-9]".toRegex()
    val condition = capitalLetters.containsMatchIn(password) && numbers.containsMatchIn(password)
    isValid = if (email.isEmpty()) {
        emailError.value = "Field is required"
        false
    } else if (email.length < 10 || !Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() && !Patterns.PHONE.matcher(email).matches()
    ) {
        emailError.value = "Please Enter a Valid Email Address or Phone"
        false
    } else if(password.isEmpty()) {
        emailError.value= ""
        passwordError.value = "Field is required"
        false
    } else if (password.length < 8) {
        emailError.value= ""
        passwordError.value = "Password must be greater than 8 characters"
        false
    } else if (!condition) {
        emailError.value= ""
        passwordError.value ="Password must contain Capital Alphabets and numbers"
        false
    } else {
        emailError.value= ""
        passwordError.value = ""
        true
    }
    return isValid
}