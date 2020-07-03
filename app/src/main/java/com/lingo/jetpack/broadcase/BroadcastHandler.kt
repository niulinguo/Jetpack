package com.lingo.jetpack.broadcase

import android.content.Context
import android.content.Intent

interface BroadcastHandler {
    fun handlerAction(action: String): Boolean

    fun onReceive(context: Context, intent: Intent)
}