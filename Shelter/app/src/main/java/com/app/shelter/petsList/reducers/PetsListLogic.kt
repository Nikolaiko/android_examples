package com.app.shelter.petsList.reducers

import com.app.shelter.petsList.model.ListFragmentNews
import com.app.shelter.petsList.model.ListFragmentState
import io.reactivex.Observable

interface PetsListLogic {
    val screenState: Observable<ListFragmentState>
    val screenNews: Observable<ListFragmentNews>

    fun refreshData()
    fun petRowSelected(petId: Int)
}