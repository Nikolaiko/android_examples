package com.app.shelter.storage.room.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.shelter.storage.room.PET_NAME_FIELD
import com.app.shelter.storage.room.PET_OWNER_ID_FIELD
import com.app.shelter.storage.room.PET_TABLE_NAME
import com.app.shelter.storage.room.PET_TYPE_FIELD

@Entity(tableName = PET_TABLE_NAME)
data class PetEntity (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = PET_NAME_FIELD) val name: String,
    @ColumnInfo(name = PET_TYPE_FIELD) val type: String,
    @ColumnInfo(name = PET_OWNER_ID_FIELD) val ownerId: Int
)
