package com.example.tradingbot.domain.validations

import androidx.compose.runtime.MutableState
import com.example.tradingbot.ui.theme.fieldIsRequired

fun ResetPasswordCredentialsValidations(
    resetPassword: String, resetPasswordError: MutableState<String>,
    resetConfirmPassword: String, resetConfirmPasswordError: MutableState<String>
    ): Boolean {
    val capitalLetters = "[A-Z]".toRegex()
    val numbers = "[0-9]".toRegex()
    val condition =
        capitalLetters.containsMatchIn(resetPassword) && numbers.containsMatchIn(resetPassword)

    var isValid = if (resetPassword.isEmpty()) {
        resetPasswordError.value = fieldIsRequired
        resetConfirmPasswordError.value=""
        false
    }   else if (resetPassword.length < 8) {
        resetPasswordError.value = "Password must be greater than 8 characters"
        resetConfirmPasswordError.value=""
        false
    } else if (!condition) {
        resetPasswordError.value = "Password must contain Capital Alphabets and numbers"
        resetConfirmPasswordError.value=""
        false
    } else if (resetPassword != resetConfirmPassword) {
        resetPasswordError.value = "Password and Confirm Password don't match"
        resetConfirmPasswordError.value = "Password and Confirm Password don't match"
        false
    }else {
        resetConfirmPasswordError.value=""
        resetPasswordError.value = ""
        true
    }
    return isValid
}