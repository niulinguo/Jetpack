package com.lingo.jetpack.broadcase.handler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.RemoteInput
import com.lingo.jetpack.R
import com.lingo.jetpack.broadcase.BroadcastActions
import com.lingo.jetpack.broadcase.BroadcastHandler
import com.lingo.jetpack.intent.IntentUtils
import com.lingo.jetpack.intent.PendingIntentUtils
import com.lingo.jetpack.notification.NotificationActionUtils
import com.lingo.jetpack.notification.NotificationUtils

class TestHandler : BroadcastHandler {

    override fun handlerAction(action: String): Boolean {
        return action == BroadcastActions.TEST
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, intent.toUri(Intent.URI_INTENT_SCHEME))
        val notificationId = intent.getIntExtra(context.getString(R.string.notification_key_id), -1)
        Log.i(TAG, "notificationId=$notificationId")
        val replyData: Bundle? = RemoteInput.getResultsFromIntent(intent)
        if (replyData != null) {
            for (key in replyData.keySet()) {
                Log.i(TAG, key + '=' + replyData.get(key))
            }
            val replyKey = context.getString(R.string.notification_key_reply)
            val reply = replyData.getString(replyKey)
                ?: throw IllegalArgumentException("can not find reply, key:$replyKey")
            NotificationUtils.showNotification(
                context, notificationId, NotificationUtils.createAlertNotificationBuilder(
                    context,
                    "测试", "只是一个测试而已",
                    PendingIntentUtils.activityPendingIntent(
                        context,
                        IntentUtils.openSettingsWithNewTaskIntent(context)
                    )
                )
                    .addAction(
                        NotificationActionUtils.createTestReplyActionBuilder(
                            context, PendingIntentUtils.broadcastReplyPendingIntent(
                                context,
                                2,
                                IntentUtils.sendTestBroadcast(context, notificationId)
                            )
                        ).build()
                    )
                    .setRemoteInputHistory(arrayOf(reply))
            )
        }
    }

    companion object {
        private const val TAG = "TestHandler"
    }
}