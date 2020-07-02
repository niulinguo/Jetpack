package com.lingo.jetpack.bubble

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Person
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import com.lingo.jetpack.AppContext
import com.lingo.jetpack.R
import com.lingo.jetpack.ui.bubbles.BubbleActivity

object BubbleUtils {

    @RequiresApi(Build.VERSION_CODES.Q)
    fun bubble() {
        // Create bubble intent
        val target = Intent(AppContext, BubbleActivity::class.java)
        val bubbleIntent = PendingIntent.getActivity(AppContext, 0, target, 0)

        // Create bubble metadata
        val bubbleData = Notification.BubbleMetadata.Builder()
            .setDesiredHeight(600)
            .setIcon(Icon.createWithResource(AppContext, R.drawable.ic_launcher_foreground))
            .setIntent(bubbleIntent)
            .build()

        // Create notification
        val chatBot = Person.Builder()
            .setBot(true)
            .setName("BubbleBot")
            .setImportant(true)
            .build()

        val builder = Notification.Builder(AppContext, "default")
            .setContentIntent(bubbleIntent)
            .setSmallIcon(Icon.createWithResource(AppContext, R.drawable.ic_launcher_foreground))
            .setBubbleMetadata(bubbleData)
            .addPerson(chatBot)

        val notificationManager: NotificationManager =
            AppContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }
}