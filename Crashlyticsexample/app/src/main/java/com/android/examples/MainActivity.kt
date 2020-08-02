package com.android.examples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(BuildConfig.enableDebugLogging)
    }
}