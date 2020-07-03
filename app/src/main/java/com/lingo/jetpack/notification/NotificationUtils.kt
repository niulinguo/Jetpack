package com.lingo.jetpack.notification

import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lingo.jetpack.R

object NotificationUtils {

    fun showNotification(
        context: Context,
        notificationId: Int,
        notificationBuilder: NotificationCompat.Builder
    ) {
        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, notificationBuilder.build())
        }
    }

    fun createAlertNotificationBuilder(
        context: Context, contentTitle: String,
        contentText: String,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        return createNotificationBuilder(
            context,
            context.getString(R.string.channel_alert_id),
            contentTitle,
            contentText,
            pendingIntent,
            NotificationCompat.PRIORITY_HIGH
        )
    }

    fun createMessageNotificationBuilder(
        context: Context, contentTitle: String,
        contentText: String,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        return createNotificationBuilder(
            context,
            context.getString(R.string.channel_message_id),
            contentTitle,
            contentText,
            pendingIntent,
            NotificationCompat.PRIORITY_LOW
        )
    }

    private fun createNotificationBuilder(
        context: Context,
        channelId: String,
        contentTitle: String,
        contentText: String,
        pendingIntent: PendingIntent,
        priority: Int
    ): NotificationCompat.Builder {
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