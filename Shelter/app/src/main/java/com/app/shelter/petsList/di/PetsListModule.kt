package com.app.shelter.petsList.di

import com.app.shelter.petsList.presenters.PetsListPresentation
import com.app.shelter.petsList.presenters.PetsListPresenter
import com.app.shelter.petsList.reducers.PetsListLogic
import com.app.shelter.petsList.reducers.PetsListReducer
import kotlinx.coroutines.CoroutineScope
import toothpick.config.Module
import toothpick.ktp.binding.bind

class PetsListModule constructor(
    scope: CoroutineScope
) : Module() {
    init {
        bind<CoroutineScope>().toInstance(scope)

        bind<PetsListLogic>()
            .toClass<PetsListReducer>()
            .singleton()

        bind<PetsListPresentation>()
            .toClass<PetsListPresenter>()
            .singleton()
    }
}