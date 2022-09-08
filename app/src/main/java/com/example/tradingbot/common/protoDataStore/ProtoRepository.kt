package com.example.tradingbot.common.protoDataStore

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import com.example.tradingbot.LoginModel
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProtoRepository @Inject constructor(
    var context: Application,
    val dataStore: DataStore<LoginModel>
) {

    init {

        Log.d("ProtoRepository", "init")
    }


    val readProto = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("Error", exception.message.toString())
                emit(LoginModel.getDefaultInstance())

            } else {
                throw exception
            }
        }

    suspend fun updateValue(token: String) {

        dataStore.updateData { it -> it.toBuilder().setDeviceToken(token).build() }

    }

    suspend fun updateValueLogin(email: String, password: String) {

        dataStore.updateData { it -> it.toBuilder().setEmailAddress(email).build() }
        dataStore.updateData { it -> it.toBuilder().setMobileNumber(email).build() }
        dataStore.updateData { it -> it.toBuilder().setPassword(password).build() }

    }

    suspend fun updateValueSecurityToken(securityToken: String, isRemembered: Boolean) {

        dataStore.updateData { it -> it.toBuilder().setSecurityToken(securityToken).build() }
        dataStore.updateData { it -> it.toBuilder().setIsRemembered(isRemembered).build() }

    }

    suspend fun updateValueMobileNumber(MobileNumber: String) {

        dataStore.updateData { it -> it.toBuilder().setMyFanTextMobileNumber(MobileNumber).build() }

    }

    suspend fun updateValueNotificationSettings(
        isFanEnrollmentPushNotif: Boolean,
        isFanMsgPushNotif: Boolean,
        isPushNotification: Boolean
    ) {

        dataStore.updateData { it ->
            it.toBuilder().setIsFanEnrollmentPushNotif(isFanEnrollmentPushNotif).build()
        }
        dataStore.updateData { it ->
            it.toBuilder().setIsFanMsgPushNotif(isFanMsgPushNotif).build()
        }
        dataStore.updateData { it ->
            it.toBuilder().setIsPushNotification(isPushNotification).build()
        }

    }
}

