package ch.b.retrofitandcoroutines.presentation

import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.R
import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build

import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import ch.b.retrofitandcoroutines.presentation.core.Ui.MainActivity


class CustomService : Service() {
    private var mNM: NotificationManager? = null
    private val NOTIFICATION: Int = R.string.paste
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mNM!!.cancel(NOTIFICATION)

        // Tell the user we stopped.
        Toast.makeText(this, "сервис destroyed", Toast.LENGTH_SHORT).show()
    }
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startMyOwnForeground() else startForeground(
            1,
            Notification()
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyOwnForeground() {
        val NOTIFICATION_CHANNEL_ID = "com.example.simpleapp"
        val channelName = "My Background Service"
        val chan = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification: Notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.ic_btn_speak_now)
            .setContentTitle("App is running in background")
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(2, notification)
    }


    override fun startService(service: Intent?): ComponentName? {
        return super.startService(service)
    }
    private fun showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        val text = "Test two"

        // The PendingIntent to launch our activity if the user selects this notification
        val contentIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java), 0
        )

        // Set the info for the views that show in the notification panel.
        val notification: Notification = Notification.Builder(this)
            .setTicker(text) // the status text
            .setWhen(System.currentTimeMillis()) // the time stamp
            .setContentTitle("content title") // the label of the entry
            .setContentText(text) // the contents of the entry
            .setContentIntent(contentIntent) // The intent to send when the entry is clicked
            .build()

        // Send the notification.
//        mNM!!.notify(NOTIFICATION, notification)
        startForeground(1,notification)
    }

}