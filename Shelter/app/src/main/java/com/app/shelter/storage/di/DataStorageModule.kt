package com.app.shelter.storage.di

import com.app.shelter.storage.DataStorage
import com.app.shelter.storage.LocalDataStorage
import kotlinx.coroutines.CoroutineScope
import toothpick.config.Module
import toothpick.ktp.binding.bind
import javax.inject.Inject

class DataStorageModule : Module() {
    init {
        bind<DataStorage>().toClass<LocalDataStorage>().singleton()
    }
}
