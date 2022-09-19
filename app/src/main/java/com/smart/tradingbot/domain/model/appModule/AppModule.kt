package com.smart.tradingbot.domain.model.appModule

import com.smart.tradingbot.TradingBotApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    var loginResponseModel= LoginResponseModel(null,-1,"No internet","Yes",0)

    @Singleton
    @Provides
    @Named("myFanTextMobileNumber")
    fun myFanTextMobileNumber() = "(000) 000-0000"

//    @Singleton
//    @Provides
//    @Named("loginResponseModel")
//    fun loginResponseModel() = loginResponseModel

    @Singleton
    @Provides
    @Named("lastFourDigits")
    fun lastFourDigits() = ""

    @Singleton
    @Provides
    @Named("cardType")
    fun cardType() = "No payment Method"

    @Singleton
    @Provides
    @Named("cardExpirationDate")
    fun cardExpirationDate() = ""

    @Singleton
    @Provides
    fun provideDataStore()= TradingBotApp.instance.dataStore

    @Singleton
    @Provides
    fun provideAppContext( )= TradingBotApp.instance
}