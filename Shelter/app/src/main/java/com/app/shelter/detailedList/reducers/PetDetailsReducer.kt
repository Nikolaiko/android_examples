package com.app.shelter.detailedList.reducers

import com.app.shelter.storage.DataStorage
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PetDetailsReducer @Inject constructor(
    private val dataStorage: DataStorage,
    private val scope: CoroutineScope
) : DetailedLogic {

}