package com.lingo.jetpack

import android.app.Application
import android.content.ContextWrapper
import com.lingo.jetpack.notification.NotificationChannelUtils

private lateinit var INSTANCE: Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        NotificationChannelUtils.init(this)
    }
}

object AppContext : ContextWrapper(INSTANCE)