package com.abhishek.todoapp.helpers

import android.content.Context
import androidx.startup.Initializer
import com.abhishek.todoapp.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber Initializer is Initialized")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
