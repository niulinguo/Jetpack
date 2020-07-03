package com.lingo.jetpack.notification

import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import com.lingo.jetpack.R

object NotificationActionUtils {

    fun createTestActionBuilder(
        context: Context,
        pendingIntent: PendingIntent
    ): NotificationCompat.Action.Builder {
        return createActionBuilder(
            R.drawable.ic_launcher_foreground,
            context.getString(R.string.notification_action_test),
            pendingIntent
        )
    }

    private fun createActionBuilder(
        icon: Int,
        title: String,
        pendingIntent: PendingIntent
    ): NotificationCompat.Action.Builder {
        return NotificationCompat.Action.Builder(icon, title, pendingIntent)
    }
}