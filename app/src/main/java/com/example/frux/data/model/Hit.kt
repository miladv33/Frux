package com.example.frux.data.model

import com.example.frux.data.model.base.Model
import com.example.frux.data.remote.model.HitDTO

data class Hit(
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val largeImageURL: String,
    val likes: Int,
    val previewHeight: Int,
    val tags: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
):Model()