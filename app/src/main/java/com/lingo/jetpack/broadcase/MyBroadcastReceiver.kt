package com.lingo.jetpack.broadcase

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.lingo.jetpack.broadcase.handler.TestHandler

class MyBroadcastReceiver : BroadcastReceiver() {

    private val broadcastHandlers = mutableListOf<BroadcastHandler>()

    init {
        broadcastHandlers.add(TestHandler())
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val action = intent?.action ?: return
        for (broadcastHandler in broadcastHandlers) {
            if (broadcastHandler.handlerAction(action)) {
                broadcastHandler.onReceive(context, intent)
            }
        }
    }
}