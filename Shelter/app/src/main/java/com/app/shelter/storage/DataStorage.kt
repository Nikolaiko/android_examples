package com.app.shelter.storage

import com.app.shelter.storage.model.PetEntityShortData
import com.app.shelter.storage.room.entitys.OwnerEntity
import com.app.shelter.storage.room.entitys.PetEntity

interface DataStorage {
    suspend fun prePopulateIfNeeded()

    suspend fun getPetById(id: Int): PetEntity
    suspend fun getOwnerById(id: Int): OwnerEntity
    suspend fun getShortDataList(): List<PetEntityShortData>
    suspend fun getOwnerPetsList(ownerId: Int): List<PetEntity>
}