package com.lingo.jetpack.notification

import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
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

    fun createTestReplyActionBuilder(
        context: Context,
        pendingIntent: PendingIntent
    ): NotificationCompat.Action.Builder {
        val remoteInput: RemoteInput =
            RemoteInput.Builder(context.getString(R.string.notification_key_reply)).run {
                setLabel(context.getString(R.string.notification_action_test_input_label))
                build()
            }
        return createActionBuilder(
            R.drawable.ic_launcher_foreground,
            context.getString(R.string.notification_action_test_input_label),
            pendingIntent
        ).addRemoteInput(remoteInput)
    }

    private fun createActionBuilder(
        icon: Int,
        title: String,
        pendingIntent: PendingIntent
    ): NotificationCompat.Action.Builder {
        return NotificationCompat.Action.Builder(icon, title, pendingIntent)
    }
}