package com.lingo.jetpack.intent

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object PendingIntentUtils {

    fun activityPendingIntent(context: Context, intent: Intent): PendingIntent {
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    fun broadcastPendingIntent(context: Context, intent: Intent): PendingIntent {
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    fun broadcastReplyPendingIntent(context: Context, replyId: Int, intent: Intent): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            replyId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}