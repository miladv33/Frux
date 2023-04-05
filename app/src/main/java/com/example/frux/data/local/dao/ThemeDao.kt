package com.example.frux.data.local.dao

import androidx.room.*
import com.example.frux.data.model.Theme

@Dao
interface ThemeDao {
    // Insert a new theme or replace an existing one
    // Insert or update a theme with id = 0
    @Query("INSERT OR REPLACE INTO theme (id, theme_is_dark) VALUES (0, :themeIsDark)")
    suspend fun insertOrUpdateTheme(themeIsDark: Boolean)

    @Transaction
    @Query("SELECT * FROM theme WHERE id = 0")
    suspend fun getTheme(): Theme?
}