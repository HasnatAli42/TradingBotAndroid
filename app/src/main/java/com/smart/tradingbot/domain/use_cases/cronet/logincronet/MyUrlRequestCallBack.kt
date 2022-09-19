package com.smart.tradingbot.domain.use_cases.cronet.logincronet
//
//import android.util.Log
//import com.example.tradingbot.domain.model.loginResponseModel.LoginResponseModel
//import com.example.tradingbot.domain.model.viewModel.LoginResponseViewModel
//import com.example.tradingbot.presentation.login.ActivityLogin
//import com.example.tradingbot.presentation.login.LoginApiCallBack
//import com.google.gson.Gson
//import org.chromium.net.CronetException
//import org.chromium.net.UrlRequest
//import org.chromium.net.UrlResponseInfo
//import java.io.ByteArrayOutputStream
//import java.io.IOException
//import java.nio.ByteBuffer
//import java.nio.channels.Channels
//
//private const val TAG = "LoginActivity"
//
//class MyUrlRequestCallback(loginApiCallBack: LoginApiCallBack) : UrlRequest.Callback() {
//
//    private val bytesReceived = ByteArrayOutputStream()
//    private val receiveChannel = Channels.newChannel(bytesReceived)
//    var start: Long = 0
//    private var stop: Long = 0
//
//
//
//    override fun onRedirectReceived(request: UrlRequest?, info: UrlResponseInfo?, newLocationUrl: String?) {
//        Log.d(TAG, "onRedirectReceived method called.")
//        // You should call the request.followRedirect() method to continue
//        // processing the request.
//        if (info?.httpStatusCode == 503) {
//            request?.followRedirect()
//        } else {
//            request?.cancel()
//        }
//    }
//
//    override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
//        Log.d(TAG, "onResponseStarted method called.")
//        // You should call the request.read() method before the request can be
//        // further processed. The following instruction provides a ByteBuffer object
//        // with a capacity of 102400 bytes to the read() method.
//            request?.read(ByteBuffer.allocateDirect(102400))
//    }
//
//    override fun onReadCompleted(request: UrlRequest?, info: UrlResponseInfo?, byteBuffer: ByteBuffer?) {
//        // The response body is available, process byteBuffer.
//        Log.d(TAG, "onReadCompleted method called.")
//        Log.d(TAG, "Byte Buffer before flip $byteBuffer")
//        byteBuffer?.flip()
//        Log.d(TAG, "Byte Buffer after flip $byteBuffer")
//        try {
//            receiveChannel.write(byteBuffer)
//        } catch (e: IOException) {
//            android.util.Log.d(TAG, "IOException during ByteBuffer read. Details: ", e)
//        }
//
//        // Continue reading the response body by reusing the same buffer
//        // until the response has been completed.
//        byteBuffer?.clear()
//        request?.read(byteBuffer)
//
//    }
//
//    override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
//        val byteArray = bytesReceived.toByteArray()
//        var response = String(byteArray)
//        var loginResponseModel = Gson().fromJson(response, LoginResponseModel::class.java)
//
//        Log.d(ActivityLogin::class.java.simpleName, "Length ${loginResponseModel.errorMessage?.length.toString()}" )
//        Log.d(ActivityLogin::class.java.simpleName, "${loginResponseModel.errorMessage}" )
//
//        Log.d(TAG, "onSucceeded method called.")
//        Log.d("LoginActivity","byteArray at onSucceeded = $response")
////        Log.d("LoginActivity","Email address = ${gson.data.emailAddress}")
//        Log.d("LoginActivity","byteArray Size at onSucceeded = ${byteArray.size}")
//        loginApiCallBack.onSuccess(loginResponseModel)
//
//    }
//
//    override fun onFailed(request: UrlRequest?, info: UrlResponseInfo?, error: CronetException?) {
////        The request has failed. If possible, handle the error.
//        Log.d(TAG, "The request failed.", error)
//        loginApiCallBack.onFailure()
//    }
//}