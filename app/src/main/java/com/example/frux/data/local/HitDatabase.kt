package com.example.frux.data.local

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.*
import com.example.frux.data.local.dao.HitDao
import com.example.frux.data.model.Hit
import com.example.frux.utilities.Constants.DATABASE_NAME

@Database(entities = [Hit::class], version = 2, exportSchema = false)
abstract class HitDatabase : RoomDatabase() {
    // Declare the DAOs that work with the database
    abstract fun hitDao(): HitDao
    val MIGRATION_From_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE hits ADD COLUMN searchedKey TEXT DEFAULT NULL")
        }
    }

    // Provide a singleton instance of the database using a companion object
    companion object {
        // Volatile annotation ensures that the value of INSTANCE is always up-to-date
        @Volatile
        private var INSTANCE: HitDatabase? = null

        // Synchronized annotation ensures that only one thread can access the database at a time
        @SuppressLint("RestrictedApi")
        @Synchronized
        fun getInstance(context: Context): HitDatabase {
            // If the instance is null, create a new one using Room.databaseBuilder()
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    HitDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2)
                    .build()
            }
            // Return the instance
            return INSTANCE!!
        }
    }

}