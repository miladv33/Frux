package com.example.frux.data.repository

import com.example.frux.data.local.dao.HitDao
import com.example.frux.data.map.mappers.PixarImageMapper
import com.example.frux.data.model.PixabayImage
import com.example.frux.data.remote.service.PixabayService
import com.example.frux.data.repository.base.IBaseRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

// The repository class that fetches and maps the data from the service
@Module
@InstallIn(ViewModelComponent::class)
class PixabayImageRepository @Inject constructor(
    private val pixabayService: PixabayService,
    private val pixarImageMapper: PixarImageMapper,
    private val dao: HitDao
) : IBaseRepository.PixabayImageRepository() {

    override suspend fun searchImage(searchKey: String, imageType: String): Result<PixabayImage> {
        val searchedImage = pixabayService.getImages(query = searchKey, imageType = imageType)
        val pixarImage = pixarImageMapper.map(searchedImage)
        pixarImage.getOrNull()?.hits?.let { dao.insertAll(it) }
        return pixarImage
    }
}