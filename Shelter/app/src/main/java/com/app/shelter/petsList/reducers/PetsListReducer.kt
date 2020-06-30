package com.app.shelter.petsList.reducers

import com.app.shelter.petsList.model.ListFragmentState
import com.app.shelter.storage.DataStorage
import javax.inject.Inject

class PetsListReducer @Inject constructor(
    val dataStorage: DataStorage
): PetsListLogic {
    private var state = ListFragmentState()

    override fun refreshData() {

    }
}