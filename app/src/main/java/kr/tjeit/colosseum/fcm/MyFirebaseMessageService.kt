package kr.tjeit.colosseum.fcm

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessageService : FirebaseMessagingService() {

//    FCM서버에서 새 토큰을 발급해주면 실행되는 함수.
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("새로발급된토큰", token)
    }

//    푸시알림이 수신되면 실행될 함수.
    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        Log.d("푸시알림", "수신확인")

    Log.d("푸시제목", message?.notification?.title)
    Log.d("푸시바디", message?.notification?.body.toString())

        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, message?.notification?.body.toString(), Toast.LENGTH_SHORT).show()
        }



    }

}