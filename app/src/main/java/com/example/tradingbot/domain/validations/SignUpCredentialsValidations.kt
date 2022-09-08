package com.example.tradingbot.domain.validations

import android.util.Patterns
import androidx.compose.runtime.MutableState
import com.example.tradingbot.ui.theme.fieldIsRequired
import com.example.tradingbot.ui.theme.nameRegexError

import org.chromium.base.Log

fun SignUpCredentialsValidations(
    firstName: String, firstNameError: MutableState<String>,
    lastName: String, lastNameError: MutableState<String>,
    mobileNumber: String, mobileNumberError: MutableState<String>,
    email: String, emailError: MutableState<String>,
    setPassword: String, setPasswordError: MutableState<String>,
    confirmPassword: String, confirmPasswordError: MutableState<String>,
    dateOFBirth:String, dateOFBirthError: MutableState<String>,
    streetAddress1:String,streetAddress1Error:MutableState<String>,
    streetAddress2:String,streetAddress2Error:MutableState<String>,
    cityAddress:String,cityAddressError:MutableState<String>,
    countryAddress:String,countryAddressError:MutableState<String>,
    stateAddress:String,stateAddressError:MutableState<String>,
    zipAddress:String,zipAddressError:MutableState<String>,
    termsAgreed:Boolean, termsAgreedError: MutableState<String>,
    paymentMethod:String, paymentMethodError: MutableState<String>,
): Boolean {
    val capitalLetters = "[A-Z]".toRegex()
    val numbers = "[0-9]".toRegex()
    val condition =
        capitalLetters.containsMatchIn(setPassword) && numbers.containsMatchIn(setPassword)
    val condition1 =
        numbers.containsMatchIn(paymentMethod)

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
    } else if (mobileNumber.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = fieldIsRequired
        false
    } else if (!Patterns.PHONE.matcher(mobileNumber).matches() && mobileNumber.length!=10){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = "Please Enter a Valid Phone Number"
        false
    }else if (email.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = fieldIsRequired
        false
    } else if (email.length < 10 || !Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = "Please Enter a Valid Email Address"
        false
    } else if (setPassword.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = fieldIsRequired
        false
    } else if (setPassword.length < 8) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = "Password must be greater than 8 characters"
        false
    } else if (!condition) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = "Password must contain Capital Alphabets and numbers"
        false
    } else if (confirmPassword != setPassword) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = "Password and Confirm Password don't match"
        false
    }else if(dateOFBirth.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = fieldIsRequired
        false
    }else if(streetAddress1.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = fieldIsRequired
        false
    }else if(streetAddress2.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress2Error.value = fieldIsRequired
        false
    }else if(cityAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = fieldIsRequired
        false
    }else if(countryAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = fieldIsRequired
        false
    }else if(stateAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = fieldIsRequired
        false
    }else if(zipAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = ""
        zipAddressError.value = fieldIsRequired
        false
    }else if(!condition1){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = ""
        zipAddressError.value = ""
        paymentMethodError.value = "Add payment method to continue"
        false
    }else if(!termsAgreed){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = ""
        zipAddressError.value = ""
        paymentMethodError.value = ""
        termsAgreedError.value = "Kindly Accept Terms to Continue"
        false
    }else {
        firstNameError.value=""
        lastNameError.value = ""
        mobileNumberError.value =""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value=""
        streetAddress2Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = ""
        zipAddressError.value = ""
        termsAgreedError.value = ""
        paymentMethodError.value = ""
        true
    }
    return isValid
}






fun SignUpCardCredentialsValidations(
    firstName: String, firstNameError: MutableState<String>,
    lastName: String, lastNameError: MutableState<String>,
    mobileNumber: String, mobileNumberError: MutableState<String>,
    email: String, emailError: MutableState<String>,
    setPassword: String, setPasswordError: MutableState<String>,
    confirmPassword: String, confirmPasswordError: MutableState<String>,
    dateOFBirth:String, dateOFBirthError: MutableState<String>,
    streetAddress1:String,streetAddress1Error:MutableState<String>,
    streetAddress2:String,streetAddress2Error:MutableState<String>,
    cityAddress:String,cityAddressError:MutableState<String>,
    countryAddress:String,countryAddressError:MutableState<String>,
    stateAddress:String,stateAddressError:MutableState<String>,
    zipAddress:String,zipAddressError:MutableState<String>,
    termsAgreed:Boolean, termsAgreedError: MutableState<String>,
): Boolean {
    val capitalLetters = "[A-Z]".toRegex()
    val numbers = "[0-9]".toRegex()
    val condition =
        capitalLetters.containsMatchIn(setPassword) && numbers.containsMatchIn(setPassword)

    var isValid = if (firstName.isEmpty()) {
        firstNameError.value = fieldIsRequired
        false
    } else if (lastName.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = fieldIsRequired
        false
    } else if (mobileNumber.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = fieldIsRequired
        false
    } else if (!Patterns.PHONE.matcher(mobileNumber).matches() && mobileNumber.length!=10){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = "Please Enter a Valid Phone Number"
        false
    }else if (email.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = fieldIsRequired
        false
    } else if (email.length < 10 || !Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = "Please Enter a Valid Email Address"
        false
    } else if (setPassword.isEmpty()) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = fieldIsRequired
        false
    } else if (setPassword.length < 8) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = "Password must be greater than 8 characters"
        false
    } else if (!condition) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = "Password must contain Capital Alphabets and numbers"
        false
    } else if (confirmPassword != setPassword) {
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = "Password and Confirm Password don't match"
        false
    }else if(dateOFBirth.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = fieldIsRequired
        false
    }else if(streetAddress1.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = fieldIsRequired
        false
    }else if(streetAddress2.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress2Error.value = fieldIsRequired
        false
    }else if(cityAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = fieldIsRequired
        false
    }else if(countryAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = fieldIsRequired
        false
    }else if(stateAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = fieldIsRequired
        false
    }else if(zipAddress.isEmpty()){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = ""
        zipAddressError.value = fieldIsRequired
        false
    }else if(!termsAgreed){
        firstNameError.value = ""
        lastNameError.value = ""
        mobileNumberError.value = ""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value = ""
        streetAddress1Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = ""
        zipAddressError.value = ""
        termsAgreedError.value = "Kindly Accept Terms to Continue"
        false
    }else {
        firstNameError.value=""
        lastNameError.value = ""
        mobileNumberError.value =""
        emailError.value = ""
        setPasswordError.value = ""
        confirmPasswordError.value = ""
        dateOFBirthError.value = ""
        streetAddress1Error.value=""
        streetAddress2Error.value = ""
        cityAddressError.value = ""
        countryAddressError.value = ""
        stateAddressError.value = ""
        zipAddressError.value = ""
        true
    }
    return isValid
}