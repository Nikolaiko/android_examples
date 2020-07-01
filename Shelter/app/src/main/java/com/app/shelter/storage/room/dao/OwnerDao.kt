package com.app.shelter.storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.app.shelter.storage.room.OWNER_TABLE_NAME
import com.app.shelter.storage.room.entitys.OwnerEntity

@Dao
interface OwnerDao {
    @Query("SELECT * FROM $OWNER_TABLE_NAME")
    suspend fun getAll(): List<OwnerEntity>

    @Query("SELECT * FROM $OWNER_TABLE_NAME WHERE id = :userId")
    suspend fun getById(userId: Int): List<OwnerEntity>

    @Insert
    suspend fun insertAll(vararg owners: OwnerEntity)

    @Delete
    suspend fun delete(owner: OwnerEntity)
}