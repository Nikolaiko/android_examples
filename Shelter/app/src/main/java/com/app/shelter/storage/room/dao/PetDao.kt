package com.app.shelter.storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.app.shelter.storage.room.PET_NAME_FIELD
import com.app.shelter.storage.room.PET_TABLE_NAME
import com.app.shelter.storage.room.PET_TYPE_FIELD
import com.app.shelter.storage.room.entitys.PetEntity
import com.app.shelter.storage.model.PetEntityShortData
import com.app.shelter.storage.room.PET_OWNER_ID_FIELD

@Dao
interface PetDao {
    @Query("SELECT * FROM $PET_TABLE_NAME")
    suspend fun getAll(): List<PetEntity>

    @Query("SELECT * FROM $PET_TABLE_NAME WHERE id = :petId")
    suspend fun getById(petId: Int): List<PetEntity>

    @Query("SELECT $PET_NAME_FIELD, $PET_TYPE_FIELD, id FROM $PET_TABLE_NAME")
    suspend fun getShortData(): List<PetEntityShortData>

    @Query("SELECT * FROM $PET_TABLE_NAME WHERE $PET_OWNER_ID_FIELD = :ownerId")
    suspend fun getAllOwnerPets(ownerId: Int): List<PetEntity>

    @Insert
    suspend fun insertAll(vararg pets: PetEntity)

    @Delete
    suspend fun delete(pet: PetEntity)
}