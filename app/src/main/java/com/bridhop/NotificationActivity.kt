package com.bridhop

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.NotificationCompat
import java.lang.Exception

const val CHANNEL_ID = "notifications"

class NotificationActivity : AppCompatActivity() {
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        createNotificationChannel()

        val backButton = findViewById<Button>(R.id.back)
        val generateNotificationButton = findViewById<Button>(R.id.generate_notification)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        generateNotificationButton.setOnClickListener{
            val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, CepActivity::class.java), PendingIntent.FLAG_IMMUTABLE)
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)

            builder.setContentTitle("Go to Cep search")
            builder.setSmallIcon(R.drawable.pointer)
            builder.setLargeIcon(Library.decoder())
            builder.setContentIntent(pendingIntent)
            builder.priority = NotificationCompat.PRIORITY_DEFAULT

            val style = NotificationCompat.InboxStyle()

            style.addLine("Hello guy.")
            builder.setStyle(style)

            val notification = builder.build()

            notification.flags = Notification.FLAG_AUTO_CANCEL

            notificationManager?.notify(R.drawable.ic_launcher_background, notification)

            try{
                val song = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val ringtone = RingtoneManager.getRingtone(this, song)

                ringtone.play()
            } catch(error: Exception){
                Log.e("error", error.toString())
            }
        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply{ description = descriptionText }

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager?.createNotificationChannel(channel)
        } else notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}
