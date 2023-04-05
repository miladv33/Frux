package com.example.frux.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.frux.data.model.Theme

@Dao
interface ThemeDao {
    // Insert a new theme or replace an existing one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheme(theme: Theme)
}