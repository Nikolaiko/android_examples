package com.app.shelter.storage.room.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.shelter.storage.room.OWNER_NAME_FIELD
import com.app.shelter.storage.room.OWNER_TABLE_NAME

@Entity(tableName = OWNER_TABLE_NAME)
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = OWNER_NAME_FIELD)
    val name: String
)