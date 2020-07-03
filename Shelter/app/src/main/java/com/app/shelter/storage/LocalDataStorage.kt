package com.app.shelter.storage

import android.content.Context
import androidx.room.Room
import com.app.shelter.core.model.PetType
import com.app.shelter.storage.model.PetEntityShortData
import com.app.shelter.storage.room.DATABASE_NAME
import com.app.shelter.storage.room.database.ShelterRoomDatabase
import com.app.shelter.storage.room.entitys.OwnerEntity
import com.app.shelter.storage.room.entitys.PetEntity
import com.app.shelter.storage.room.migrations.MigrationFrom1To2
import javax.inject.Inject

class LocalDataStorage @Inject constructor(
    appContext: Context
) : DataStorage {

    private val database: ShelterRoomDatabase = Room.databaseBuilder(
        appContext,
        ShelterRoomDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(MigrationFrom1To2())
        .build()

    override suspend fun prePopulateIfNeeded() {
        val petsDao = database.petDao()
        val ownersDao = database.ownerDao()

        val allPets = petsDao.getAll()
        if (allPets.isEmpty()) {
            val owner1 = OwnerEntity(name = "Bruce", lastName = "Willis")
            val owner2 = OwnerEntity(name = "Madonna", lastName = "Petrovna")
            ownersDao.insertAll(owner1, owner2)

            val allOwners = ownersDao.getAll()
            val pet1 = PetEntity(
                name = "Bruce",
                type = PetType.DOG.name,
                ownerId = allOwners.first().id ?: -1)
            val pet2 = PetEntity(
                name = "Madonna",
                type = PetType.CAT.name,
                ownerId = allOwners.last().id ?: -1)
            petsDao.insertAll(pet1, pet2)
        }
    }

    override suspend fun getPetById(id: Int): PetEntity {
        return database.petDao().getById(id).first()
    }

    override suspend fun getOwnerById(id: Int): OwnerEntity {
        return database.ownerDao().getById(id).first()
    }

    override suspend fun getShortDataList(): List<PetEntityShortData> {
        return database.petDao().getShortData()
    }

    override suspend fun getOwnerPetsList(ownerId: Int): List<PetEntity> {
        return database.petDao().getAllOwnerPets(ownerId)
    }
}
