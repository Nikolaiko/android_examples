package com.app.shelter.petsList.reducers

import com.app.shelter.petsList.model.ListFragmentState
import io.reactivex.Observable

interface PetsListLogic {
    val screenState: Observable<ListFragmentState>

    fun refreshData()
}