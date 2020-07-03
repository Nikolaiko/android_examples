package com.app.shelter.detailedList.di

import com.app.shelter.detailedList.presenters.DetailedPresentation
import com.app.shelter.detailedList.presenters.PetDetailsPresenter
import com.app.shelter.detailedList.reducers.DetailedLogic
import com.app.shelter.detailedList.reducers.PetDetailsReducer
import kotlinx.coroutines.CoroutineScope
import toothpick.config.Module
import toothpick.ktp.binding.bind

class DetailedListModule constructor(
    scope: CoroutineScope
): Module() {
    init {
        bind<CoroutineScope>().toInstance(scope)

        bind<DetailedLogic>()
            .toClass<PetDetailsReducer>()
            .singleton()

        bind<DetailedPresentation>()
            .toClass<PetDetailsPresenter>()
            .singleton()
    }
}
