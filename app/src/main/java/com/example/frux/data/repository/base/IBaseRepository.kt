package com.example.frux.data.repository.base

import com.example.frux.data.model.Hit
import com.example.frux.data.model.PixabayImage
import kotlinx.coroutines.flow.Flow

interface IBaseRepository {
    abstract class PixabayImageRepository() {
        abstract suspend fun searchImage(searchKey: String, imageType: String): Result<PixabayImage>
       abstract suspend fun getImagesFromServer(searchKey: String, imageType: String): Result<PixabayImage>

        abstract suspend fun saveImagesToDatabase(hits: Result<PixabayImage>)
        abstract fun getPixarImages(): Flow<List<Hit>>
        abstract fun getPixarImage(id: Int): Flow<Hit>
        abstract fun findPixarImagesByTag(query: String): Flow<List<Hit>>
    }

    abstract class PixarImageRepository {

        abstract suspend fun savePixarImages(searchKey: String, imageType: String)
    }
}