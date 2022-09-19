package com.smart.tradingbot.domain.functions

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


//const val channelId = "notification_channel"
//const val channelName = "com.maseiclinic2nd"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
//            generateNotification(
//                remoteMessage.notification!!.title!!,
//                remoteMessage.notification!!.body!!
//            )
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FireBase", "Refreshed token: $token")
    }

//    @SuppressLint("RemoteViewLayout")
//    fun getRemoteView(title: String, message: String): RemoteViews {
//        val remoteView = RemoteViews("com.maseiclinic2nd", R.layout.masri_notification)
//        remoteView.setTextViewText(R.id.titleNotification, title)
//        remoteView.setTextViewText(R.id.messageNotification, message)
//        remoteView.setImageViewResource(R.id.logoNotification, R.mipmap.masri_app_icon)
//
//        return remoteView
//    }
//
//    private fun generateNotification(title: String, message: String) {
//        val intent = Intent(this, ActivityLogin::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingActivity =
//            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
//
//        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
//            applicationContext,
//            channelId
//        ).setSmallIcon(R.mipmap.masri_app_icon).setAutoCancel(true)
//            .setVibrate(longArrayOf(1000, 1000, 1000, 1000)).setOnlyAlertOnce(true)
//            .setContentIntent(pendingActivity)
//
//        builder = builder.setContent(getRemoteView(title, message))
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel =
//                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        var randomNumber = (0..3200000).random()
//        notificationManager.notify(randomNumber, builder.build())
//        Log.d(ActivityLogin::class.java.simpleName, "your random Number = ${randomNumber.toString()}")
//
//    }

}
