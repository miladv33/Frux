package com.example.frux.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.frux.data.model.Hit

@Dao
interface HitDao {
    // Insert a single hit into the database
    @Insert
    fun insert(hit: Hit)

    // Insert a list of hits into the database
    @Insert
    fun insertAll(hits: List<Hit>)

    // Get a hit by its id from the database
    @Query("SELECT * FROM hits WHERE id = :id")
    fun get(id: Int): Hit

    // Get all hits from the database
    @Query("SELECT * FROM hits")
    fun getAll(): List<Hit>

    // Get all hits whose tag contains a given string
    @Query("SELECT * FROM hits WHERE tags LIKE '%' || :query || '%'")
    fun findByTag(query: String): List<Hit>
}