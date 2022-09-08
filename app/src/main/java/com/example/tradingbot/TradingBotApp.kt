package com.example.tradingbot

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.tradingbot.common.protoDataStore.MySerializerLoginModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TradingBotApp : Application(){

        companion object{
            lateinit var instance: TradingBotApp
        }

        override fun onCreate() {
            super.onCreate()
            instance =this

        }

    val dataStore: DataStore<LoginModel> by dataStore(
             fileName = "Login_Model",
             serializer = MySerializerLoginModel()
    )

    }
