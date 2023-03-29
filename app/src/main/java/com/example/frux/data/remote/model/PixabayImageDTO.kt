package com.example.frux.data.remote.model

data class PixabayImageDTO(
    val hits: List<HitDTO>,
    val total: Int,
    val totalHits: Int
)