package com.app.shelter.petsList.presenters

import com.app.shelter.petsList.reducers.PetsListLogic
import javax.inject.Inject

class PetsListPresenter @Inject constructor(
    private val reducer: PetsListLogic
) : PetsListPresentation {



}