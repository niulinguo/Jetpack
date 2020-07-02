package com.lingo.jetpack.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lingo.jetpack.R
import com.lingo.jetpack.ui.settings.SettingsActivity

object NotificationUtils {

    fun showNotification(
        context: Context,
        id: Int,
        notificationBuilder: NotificationCompat.Builder
    ) {
        with(NotificationManagerCompat.from(context)) {
            notify(id, notificationBuilder.build())
        }
    }

    fun createAlertNotificationBuilder(
        context: Context, contentTitle: String,
        contentText: String
    ): NotificationCompat.Builder {
        return createNotificationBuilder(
            context,
            context.getString(R.string.channel_alert_id),
            contentTitle,
            contentText,
            NotificationCompat.PRIORITY_HIGH
        )
    }

    fun createMessageNotificationBuilder(
        context: Context, contentTitle: String,
        contentText: String
    ): NotificationCompat.Builder {
        return createNotificationBuilder(
            context,
            context.getString(R.string.channel_message_id),
            contentTitle,
            contentText,
            NotificationCompat.PRIORITY_LOW
        )
    }

    private fun createNotificationBuilder(
        context: Context,
        channelId: String,
        contentTitle: String,
        contentText: String,
        priority: Int
    ): NotificationCompat.Builder {
        val intent = Intent(context, SettingsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification_small)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setPriority(priority)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }
}