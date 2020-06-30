package com.app.shelter.petsList.reducers

import com.app.shelter.storage.DataStorage
import javax.inject.Inject

class PetsListReducer @Inject constructor(
    val dataStorage: DataStorage
): PetsListLogic {

}