package com.example.tradingbot.common.protoDataStore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.tradingbot.LoginModel
import com.example.tradingbot.LoginModel.parseFrom
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream


class MySerializerLoginModel: Serializer<LoginModel> {

    override val defaultValue: LoginModel = LoginModel.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LoginModel {
        try{
            return parseFrom(input)
        }catch(exception : InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto.",exception)

        }
    }

    override suspend fun writeTo(t: LoginModel, output: OutputStream) {
        t.writeTo(output)
    }

}