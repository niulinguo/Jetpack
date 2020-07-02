package com.lingo.jetpack.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.lingo.jetpack.R

/**
 * https://developer.android.google.cn/training/notify-user/build-notification
 */
object NotificationChannelUtils {

    fun init(context: Context) {
        val notificationManager = getNotificationManager(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannels(
                listOf(
                    createAlertChannel(context),
                    createMessageChannel(context)
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createAlertChannel(context: Context): NotificationChannel {
        val id = context.getString(R.string.channel_alert_id)
        val name = context.getString(R.string.channel_alert_name)
        val desc = context.getString(R.string.channel_alert_desc)
        val importance = NotificationManager.IMPORTANCE_HIGH
        return NotificationChannel(id, name, importance).apply {
            description = desc
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMessageChannel(context: Context): NotificationChannel {
        val id = context.getString(R.string.channel_message_id)
        val name = context.getString(R.string.channel_message_name)
        val desc = context.getString(R.string.channel_message_desc)
        val importance = NotificationManager.IMPORTANCE_LOW
        return NotificationChannel(id, name, importance).apply {
            description = desc
        }
    }

    private fun getNotificationManager(context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}