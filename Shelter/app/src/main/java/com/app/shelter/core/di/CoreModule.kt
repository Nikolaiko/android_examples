package com.app.shelter.core.di

import android.content.Context
import toothpick.config.Module
import toothpick.ktp.binding.bind

class CoreModule constructor(
    appContext: Context
) : Module() {
    init {
        bind<Context>().toInstance(appContext)
    }
}