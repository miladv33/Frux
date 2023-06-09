package com.example.frux.data.repository.base

import com.example.frux.data.model.Hit
import com.example.frux.data.model.PixabayImage
import com.example.frux.data.model.Theme
import kotlinx.coroutines.flow.Flow

interface IBaseRepository {
    abstract class PixabayImageRepository() {
        abstract suspend fun searchImage(searchKey: String, imageType: String): Result<PixabayImage>
        abstract suspend fun getImagesFromServer(
            searchKey: String,
            imageType: String
        ): Result<PixabayImage>

        abstract suspend fun saveImagesToDatabase(searchKey: String, hits: Result<PixabayImage>)
        abstract fun getPixarImages(): Flow<List<Hit>>
        abstract fun getPixarImage(id: Int): Flow<Hit>
        abstract fun findPixarImagesByTag(tag: String): Flow<PixabayImage>
    }

    abstract class FruxThemeRepository {
        abstract suspend fun saveTheme(themeIsDark: Boolean)
        abstract suspend fun getTheme(): Theme?
    }
}