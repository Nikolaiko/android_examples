package com.app.shelter.storage.di

import com.app.shelter.storage.DataStorage
import com.app.shelter.storage.LocalDataStorage
import kotlinx.coroutines.CoroutineScope
import toothpick.config.Module
import toothpick.ktp.binding.bind
import javax.inject.Inject

class DataStorageModule constructor(
    scope: CoroutineScope
) : Module() {
    init {
        bind<CoroutineScope>().toInstance(scope)
        bind<DataStorage>().toClass<LocalDataStorage>().singleton()
    }
}