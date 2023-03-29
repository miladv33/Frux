package com.example.frux.data.model

data class PixabayImage(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)