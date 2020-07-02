package com.app.shelter.detailedList.presenters

import com.app.shelter.detailedList.reducers.DetailedLogic
import javax.inject.Inject

class PetDetailsPresenter @Inject constructor(
    private val reducer: DetailedLogic
) : DetailedPresentation {

}