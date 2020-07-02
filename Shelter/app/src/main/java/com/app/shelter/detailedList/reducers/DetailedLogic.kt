package com.app.shelter.detailedList.reducers

import com.app.shelter.detailedList.model.DetailedScreenNews
import com.app.shelter.detailedList.model.DetailedScreenState
import io.reactivex.Observable

interface DetailedLogic {
    val screenNews: Observable<DetailedScreenNews>
    val screenState: Observable<DetailedScreenState>

    fun loadPetData(petId: Int)
    fun backButtonTap()
}