package com.app.shelter.storage

import com.app.shelter.storage.model.PetEntityShortData

interface DataStorage {
    suspend fun prePopulateIfNeeded()
    suspend fun getShortDataList(): List<PetEntityShortData>
}