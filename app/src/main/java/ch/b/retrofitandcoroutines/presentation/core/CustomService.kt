package ch.b.retrofitandcoroutines.presentation.core

import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import android.app.PendingIntent
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.presentation.all_posts.AllPostsViewModel
import ch.b.retrofitandcoroutines.presentation.core.ui.MainActivity
import javax.inject.Inject





class CustomService : Service() {
    private var mNM: NotificationManager? = null
    private val notification: Int = R.string.author

    @Inject
    lateinit var allPostsViewModelFactory: AllPostsViewModel

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mNM!!.cancel(notification)


        Toast.makeText(this, "сервис destroyed", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "сервис onCreate", Toast.LENGTH_LONG).show()
        (applicationContext as PhotoApp).getMainAppComponent().inject(this)
        val intent = Intent(MainActivity().BROADCAST_ACTION)
        intent.putExtra(MainActivity().PARAM_TASK,123)
        sendBroadcast(intent)
        //  allPostsViewModelFactory.getPhotographers()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground()
        else
            startForeground(1, Notification())

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyOwnForeground() {
        val contentIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notifyId = "folu"
        val channelName = "My foreground Service"
        val notifyChannel = NotificationChannel(
            notifyId,
            channelName,
            NotificationManager.IMPORTANCE_NONE
        )
        notifyChannel.lightColor = Color.BLUE
        notifyChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(notifyChannel)
        val notificationBuilder = NotificationCompat.Builder(this, notifyId)
        val notification: Notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.ic_collect)
            .setContentTitle("App is running in background")
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentIntent(contentIntent)
            .build()
        startForeground(2, notification)
    }


    override fun startService(service: Intent?): ComponentName? {
        return super.startService(service)
    }
}