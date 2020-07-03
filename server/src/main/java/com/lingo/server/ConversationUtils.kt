package com.lingo.server

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

object ConversationUtils {

    fun doWork(context: Context) {
        val conversationWorkRequest = OneTimeWorkRequestBuilder<ConversationWorker>().build()
        WorkManager.getInstance(context).enqueue(conversationWorkRequest)
    }
}