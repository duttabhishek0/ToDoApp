package com.abhishek.todoapp.listeners

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.Exception

class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {
    companion object {
        private var foregroundActivityCount = 0
        fun incrementCountByOne() = foregroundActivityCount++
        fun decrementCountByOne() = foregroundActivityCount--
        fun isActivityInForeground(): Boolean = foregroundActivityCount > 0
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
    }

    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityResumed(p0: Activity) {
        incrementCountByOne()
        try {
        } catch (e: Exception) {
        }
    }

    override fun onActivityPaused(p0: Activity) {
        decrementCountByOne()
    }

    override fun onActivityStopped(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(p0: Activity) {
        TODO("Not yet implemented")
    }
}
