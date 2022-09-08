package com.example.tradingbot.data.utils

import android.content.Context
import com.example.tradingbot.TradingBotApp
import com.example.tradingbot.domain.model.others.Remember
import com.example.tradingbot.domain.model.others.ValidateCardResponse
import com.google.gson.Gson

    var mainKey = "TradingBot";
    var signInValue = "signInValue";
    var rememberValue = "rememberValue";

    var instance = TradingBotApp.instance.getSharedPreferences(mainKey, Context.MODE_PRIVATE);

    fun getSignInResponse(): ValidateCardResponse {

        var response =
            Gson().fromJson(instance.getString(signInValue, "{}"), ValidateCardResponse::class.java);
        return response
    }

    fun setSignInResponse(validateCardResponse: ValidateCardResponse) {

        var editor = instance.edit()
        editor.putString(signInValue, Gson().toJson(validateCardResponse));
        editor.apply();

    }

    fun setRemember(remember: Remember) {

        var editor = instance.edit()
        editor.putString(rememberValue, Gson().toJson(remember));
        editor.apply();

    }

    fun getRemember(): Remember {
        var response =
            Gson().fromJson(instance.getString(rememberValue, "{}"), Remember::class.java);
        return response
    }