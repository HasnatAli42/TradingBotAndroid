package com.smart.tradingbot.domain.validations

import android.util.Patterns
import androidx.compose.runtime.MutableState
import com.smart.tradingbot.ui.theme.fieldIsRequired
import com.smart.tradingbot.ui.theme.nameRegexError

fun SignUpCredentialsValidations(
    firstName: String, firstNameError: MutableState<String>,
    lastName: String, lastNameError: MutableState<String>,
    email: String, emailError: MutableState<String>,
    setPassword: String, setPasswordError: MutableState<String>,
    confirmPassword: String, confirmPasswordError: MutableState<String>,
    apiKey:String, apiKeyError:MutableState<String>,
    secretKey:String, secretKeyError:MutableState<String>,
    termsAgreed:Boolean, termsAgreedError: MutableState<String>,
): Boolean {
    val capitalLetters = "[A-Z]".toRegex()
    val numbers = "[0-9]".toRegex()
    val condition =
        capitalLetters.containsMatchIn(setPassword) && numbers.containsMatchIn(setPassword)

    var isValid = if (firstName.isEmpty()) {
        firstNameError.value = fieldIsRequired
        false
    }else if (firstName.length < 3 ) {
        firstNameError.value = nameRegexError
        false
    } else if (lastName.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = fieldIsRequired
        false
    } else if (lastName.length < 3) {
        firstNameError.value = ""
        lastNameError.value = nameRegexError
        false
    } else if (email.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = fieldIsRequired
        false
    } else if (email.length < 10 || !Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()) {
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = "Please Enter a Valid Email Address"
        false
    } else if (setPassword.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = fieldIsRequired
        false
    } else if (setPassword.length < 8) {
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = "Password must be greater than 8 characters"
        false
    } else if (!condition) {
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = "Password must contain Capital Alphabets and numbers"
        false
    } else if (confirmPassword != setPassword) {
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = "Password and Confirm Password don't match"
        false
    }else if(apiKey.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        apiKeyError.value = fieldIsRequired
        false
    }else if(secretKey.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        apiKeyError.value = ""
        secretKeyError.value = fieldIsRequired
        false
    }else if(!termsAgreed){
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        apiKeyError.value = ""
        apiKeyError.value = ""
        termsAgreedError.value = "Kindly Accept Terms to Continue"
        false
    }else {
        firstNameError.value=""
        lastNameError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        apiKeyError.value=""
        secretKeyError.value = ""
        termsAgreedError.value = ""
        true
    }
    return isValid
}






fun OtpFieldsValidation(
    otp: String, otpError: MutableState<String>,
    setPassword: String, setPasswordError: MutableState<String>,
    confirmPassword: String, confirmPasswordError: MutableState<String>,
): Boolean {
    val capitalLetters = "[A-Z]".toRegex()
    val numbers = "[0-9]".toRegex()
    val condition =
        capitalLetters.containsMatchIn(setPassword) && numbers.containsMatchIn(setPassword)

    var isValid = if (otp.isEmpty()) {
        otpError.value = fieldIsRequired
        false
    } else if (setPassword.isEmpty()) {
        otpError.value = ""
        setPasswordError.value = fieldIsRequired
        false
    } else if (setPassword.length < 8) {
        otpError.value = ""
        setPasswordError.value = "Password must be greater than 8 characters"
        false
    } else if (!condition) {
        otpError.value = ""
        setPasswordError.value = "Password must contain Capital Alphabets and numbers"
        false
    } else if (confirmPassword != setPassword) {
        otpError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = "Password and Confirm Password don't match"
        false
    }else {
        otpError.value=""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        true
    }
    return isValid
}