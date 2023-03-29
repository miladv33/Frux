package com.example.frux.data.repository.base

import com.example.frux.data.model.PixabayImage

interface IBaseRepository {
    abstract class PixabayImageRepository() {
        abstract suspend fun searchImage(searchKey: String, imageType: String): Result<PixabayImage>
    }

    abstract class PixarImageRepository {

        abstract suspend fun savePixarImages(searchKey: String, imageType: String)
    }
}