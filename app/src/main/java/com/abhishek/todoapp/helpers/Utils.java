package com.abhishek.todoapp.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatDelegate;

import com.abhishek.todoapp.listeners.ActivityLifecycleCallback;

public class Utils {
    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static boolean isApplicationInForeground() {
        return ActivityLifecycleCallback.Companion.isActivityInForeground();
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getSizeAsPerScreenWidth(double percentage) {
        return (int) (percentage * getScreenWidth());
    }

    public static int getSizeAsPerScreenHeight(double percentage) {
        return (int) (percentage * getScreenHeight());
    }

    public static void setIsDarkModeEnabled(Context context, boolean isEnabled) {
        AppCompatDelegate.setDefaultNightMode(
                isEnabled ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }
}
