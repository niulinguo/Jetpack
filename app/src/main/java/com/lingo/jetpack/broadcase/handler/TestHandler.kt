package com.lingo.jetpack.broadcase.handler

import android.content.Context
import android.content.Intent
import android.util.Log
import com.lingo.jetpack.broadcase.BroadcastActions
import com.lingo.jetpack.broadcase.BroadcastHandler

class TestHandler : BroadcastHandler {
    override fun handlerAction(action: String): Boolean {
        return action == BroadcastActions.TEST
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("TestHandler", intent.toUri(Intent.URI_INTENT_SCHEME))
    }
}