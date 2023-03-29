package com.example.frux.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.frux.data.local.dao.HitDao
import com.example.frux.data.model.Hit

@Database(entities = [Hit::class], version = 1, exportSchema = false)
abstract class HitDatabase : RoomDatabase() {
    // Declare the DAOs that work with the database
    abstract fun hitDao(): HitDao

    // Provide a singleton instance of the database using a companion object
    companion object {
        // Volatile annotation ensures that the value of INSTANCE is always up-to-date
        @Volatile
        private var INSTANCE: HitDatabase? = null

        // Synchronized annotation ensures that only one thread can access the database at a time
        @Synchronized
        fun getInstance(context: Context): HitDatabase {
            // If the instance is null, create a new one using Room.databaseBuilder()
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    HitDatabase::class.java,
                    "hit_database"
                ).build()
            }
            // Return the instance
            return INSTANCE!!
        }
    }
}