package com.app.shelter

import android.app.Application
import com.app.shelter.core.di.CoreModule
import com.app.shelter.storage.DataStorage
import com.app.shelter.storage.di.DataStorageModule
import kotlinx.coroutines.CoroutineScope
import toothpick.Toothpick
import toothpick.ktp.KTP

class ShelterApp : Application() {
    private val rootScope = KTP.openRootScope()

    override fun onCreate() {
        super.onCreate()

        rootScope
            .installModules(CoreModule(applicationContext))
    }

    fun getRootScope() = rootScope
}