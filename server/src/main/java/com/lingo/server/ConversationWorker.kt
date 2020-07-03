package com.lingo.server

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ConversationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    companion object {
        const val TAG = "ConversationWorker"
    }

    override fun doWork(): Result {
        Log.i(TAG, "ConversationWorker")
        return Result.success()
    }
}