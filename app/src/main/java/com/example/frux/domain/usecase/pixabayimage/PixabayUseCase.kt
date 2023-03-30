package com.example.frux.domain.usecase.pixabayimage

import com.example.frux.data.model.PixabayImage
import kotlinx.coroutines.flow.Flow

interface PixabayUseCase {
    fun searchImage(searchKey: String, ImageType: String): Flow<Result<PixabayImage?>>
}