package com.example.frux.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.frux.data.model.base.Model

@Entity(tableName = "hits")
data class Hit(
    @PrimaryKey
    val id: Int,
    val comments: Int,
    val downloads: Int,
    @ColumnInfo(name = "large_image_url")
    val largeImageURL: String,
    val likes: Int,
    @ColumnInfo(name = "preview_url")
    val previewURL: String,
    val tags: String,
    val user: String,
    @ColumnInfo(name = "user_image_url")
    val userImageURL: String,
    @ColumnInfo(name = "user_id")
    val user_id: Int,
    val views: Int,
    var searchedKey :String? = null
):Model()