package com.app.shelter.storage.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.shelter.storage.room.DATABASE_VERSION
import com.app.shelter.storage.room.dao.OwnerDao
import com.app.shelter.storage.room.dao.PetDao
import com.app.shelter.storage.room.entitys.OwnerEntity
import com.app.shelter.storage.room.entitys.PetEntity

@Database(entities = [OwnerEntity::class, PetEntity::class], version = DATABASE_VERSION)
abstract class ShelterRoomDatabase : RoomDatabase() {
    abstract fun ownerDao(): OwnerDao
    abstract fun petDao(): PetDao
}