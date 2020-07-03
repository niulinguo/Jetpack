package com.lingo.jetpack.intent

import android.content.Context
import android.content.Intent
import com.lingo.jetpack.broadcase.BroadcastActions
import com.lingo.jetpack.broadcase.MyBroadcastReceiver
import com.lingo.jetpack.ui.settings.SettingsActivity

object IntentUtils {

    private fun openSettingsIntent(context: Context): Intent {
        return Intent(context, SettingsActivity::class.java)
    }

    fun openSettingsWithNewTaskIntent(context: Context): Intent {
        return openSettingsIntent(context).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    fun sendTestBroadcast(context: Context, notificationId: Int): Intent {
        return Intent(context, MyBroadcastReceiver::class.java).apply {
            action = BroadcastActions.TEST
            putExtra("notification_id", notificationId)
        }
    }
}