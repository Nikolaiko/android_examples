package com.app.shelter.storage.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.shelter.storage.room.NOT_SET
import com.app.shelter.storage.room.OWNER_LAST_NAME_FIELD
import com.app.shelter.storage.room.OWNER_TABLE_NAME

class MigrationFrom1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE $OWNER_TABLE_NAME "
                + " ADD COLUMN $OWNER_LAST_NAME_FIELD TEXT NOT NULL DEFAULT '$NOT_SET'")
    }
}

