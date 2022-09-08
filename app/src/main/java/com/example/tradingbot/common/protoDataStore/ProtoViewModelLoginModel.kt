package com.example.tradingbot.common.protoDataStore

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tradingbot.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProtoViewModelLoginModel @Inject internal constructor(
    application: Application,
    dataStore: DataStore<LoginModel>
) : AndroidViewModel(application) {

    private val repository = ProtoRepository(application, dataStore)

    val tokenLiveData = repository.readProto.asLiveData()

    fun updateValue(token: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateValue(token)
    }

    fun updateValueLogin(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateValueLogin(email, password)
        }

    fun updateValueSecurityToken(securityToken:String,isRemembered:Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateValueSecurityToken(securityToken, isRemembered)
        }
    fun updateValueMobileNumber(MobileNumber:String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateValueMobileNumber(MobileNumber)
        }
    fun updateValueNotificationSettings(isFanEnrollmentPushNotif:Boolean,
    isFanMsgPushNotif:Boolean,
    isPushNotification:Boolean)= viewModelScope.launch(Dispatchers.IO) {
        repository.updateValueNotificationSettings(isFanEnrollmentPushNotif,isFanMsgPushNotif,isPushNotification)


    }

}