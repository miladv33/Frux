package com.example.frux.data.repository.base

import com.example.frux.data.model.PixabayImage

interface IBaseRepository {
    abstract class PixabayImageRepository() {
        abstract suspend fun searchImage(q: String, imageType: String): Result<PixabayImage>
    }
}