package com.example.tradingbot.domain.use_cases.cronet.logincronet

import android.util.Log
import com.example.tradingbot.TradingBotApp
import com.example.tradingbot.domain.functions.callbacks.ApiCallBack
import com.example.tradingbot.ui.theme.productionUrl
import org.chromium.net.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class loginCronet {


    private val devOrProductionUrl = productionUrl
    private val myBuilder = CronetEngine.Builder(TradingBotApp.instance.applicationContext)
    private val cronetEngine: CronetEngine = myBuilder.build()

    fun sendParametersOnlyRequest(url :String, param: String, apiCallBack: ApiCallBack) {

        val executor: Executor = Executors.newSingleThreadExecutor()

        val requestBuilder = cronetEngine.newUrlRequestBuilder(
            "${devOrProductionUrl}${url}",
            MyUrlRequestCallback(apiCallBack),
            executor
        )
//        requestBuilder.setUploadDataProvider(loginDataProvider,executor)


        val requestURL = requestBuilder!!
            .addHeader("Content-Type", "application/json")
            .setHttpMethod("POST")
            .setUploadDataProvider(UploadDataProviders.create(param.toByteArray()), executor)

//        if (!TextUtils.isEmpty(securityToken)) {
//            requestURL.addHeader("securityToken", securityToken)
//        }

        var request = requestURL.build()


//        if (isNetworkAvailable()) {
//            request.start()
//        } else {
//            var exception = Cronet.IntenetException("Please turn on internet", Throwable())
//            callBack.onFailed(request, null, exception)
//        }

        request.start()

        Log.d("LoginActivity", "Request at LoginCronet = ${request}")

    }

    fun sendSecurityTokenOnlyRequest(url:String, token: String, apiCallBack: ApiCallBack) {

        val executor: Executor = Executors.newSingleThreadExecutor()
//        var new = devOrProductionUrl
//        if (url == "logout"){
//            new =  "http://fans.fantextapp.com:7979/api/logout"
//        }

        val requestBuilder = cronetEngine.newUrlRequestBuilder(
            "${devOrProductionUrl}${url}",
            MyUrlRequestCallback(apiCallBack),
            executor
        )
//        requestBuilder.setUploadDataProvider(loginDataProvider,executor)


        val requestURL = requestBuilder!!
            .addHeader("securityToken", token)
            .addHeader("Content-Type", "application/json")
            .setHttpMethod("POST")

//        if (!TextUtils.isEmpty(securityToken)) {
//            requestURL.addHeader("securityToken", securityToken)
//        }

        var request = requestURL.build()


//        if (isNetworkAvailable()) {
//            request.start()
//        } else {
//            var exception = Cronet.IntenetException("Please turn on internet", Throwable())
//            callBack.onFailed(request, null, exception)
//        }

        request.start()

        Log.d("LoginActivity", "Request at LoginCronet = ${request}")


    }

    fun sendParametersAndSecurityTokenRequest(url :String, param: String,token: String, apiCallBack: ApiCallBack) {

        val executor: Executor = Executors.newSingleThreadExecutor()

        val requestBuilder = cronetEngine.newUrlRequestBuilder(
            "${devOrProductionUrl}${url}",
            MyUrlRequestCallback(apiCallBack),
            executor
        )

        val requestURL = requestBuilder!!
            .addHeader("Content-Type", "application/json")
            .addHeader("securityToken", token)
            .setHttpMethod("POST")
            .setUploadDataProvider(UploadDataProviders.create(param.toByteArray()), executor)

        var request = requestURL.build()
        request.start()

    }


    private inner class MyUrlRequestCallback(var apiCallBack: ApiCallBack) :
        UrlRequest.Callback() {

        private val bytesReceived = ByteArrayOutputStream()
        private val receiveChannel = Channels.newChannel(bytesReceived)
        var start: Long = 0
        private var stop: Long = 0


        override fun onRedirectReceived(
            request: UrlRequest?,
            info: UrlResponseInfo?,
            newLocationUrl: String?
        ) {
            Log.d(Companion.TAG, "onRedirectReceived method called.")
            // You should call the request.followRedirect() method to continue
            // processing the request.
            if (info?.httpStatusCode == 503) {
                request?.followRedirect()
            } else {
                request?.cancel()
            }
        }

        override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
            Log.d(Companion.TAG, "onResponseStarted method called.")
            // You should call the request.read() method before the request can be
            // further processed. The following instruction provides a ByteBuffer object
            // with a capacity of 102400 bytes to the read() method.
            request?.read(ByteBuffer.allocateDirect(102400))
        }

        override fun onReadCompleted(
            request: UrlRequest?,
            info: UrlResponseInfo?,
            byteBuffer: ByteBuffer?
        ) {
            // The response body is available, process byteBuffer.
            Log.d(loginCronet.TAG, "onReadCompleted method called.")
            Log.d(loginCronet.TAG, "Byte Buffer before flip $byteBuffer")
            byteBuffer?.flip()
            Log.d(loginCronet.TAG, "Byte Buffer after flip $byteBuffer")
            try {
                receiveChannel.write(byteBuffer)
            } catch (e: IOException) {
                android.util.Log.d(Companion.TAG, "IOException during ByteBuffer read. Details: ", e)
            }

            // Continue reading the response body by reusing the same buffer
            // until the response has been completed.
            byteBuffer?.clear()
            request?.read(byteBuffer)

        }

        override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
            val byteArray = bytesReceived.toByteArray()
            if (info != null) {
                apiCallBack.onSuccess(byteArray,info.httpStatusCode)
            }else{
                apiCallBack.onSuccess(byteArray,0)
            }

        }

        override fun onFailed(
            request: UrlRequest?,
            info: UrlResponseInfo?,
            error: CronetException?
        ) {
//        The request has failed. If possible, handle the error.
            Log.d(Companion.TAG, "The request failed.", error)
            apiCallBack.onFailure()
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}