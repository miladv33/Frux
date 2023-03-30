package com.example.frux.data.repository

import com.example.frux.data.local.dao.HitDao
import com.example.frux.data.map.mappers.PixarImageMapper
import com.example.frux.data.model.PixabayImage
import com.example.frux.data.remote.service.PixabayService
import com.example.frux.data.repository.base.IBaseRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// The repository class that fetches and maps the data from the service
@Module
@InstallIn(ViewModelComponent::class)
class PixabayImageRepository @Inject constructor(
    private val pixabayService: PixabayService,
    private val pixarImageMapper: PixarImageMapper,
    private val hitDao: HitDao
) : IBaseRepository.PixabayImageRepository() {

    override suspend fun searchImage(searchKey: String, imageType: String): Result<PixabayImage> {
        val searchedImage = getImagesFromServer(searchKey, imageType)
        saveImagesToDatabase(searchedImage)
        return searchedImage
    }

    override suspend fun getImagesFromServer(
        searchKey: String,
        imageType: String
    ): Result<PixabayImage> {
        val images = pixabayService.getImages(query = searchKey, imageType = imageType)
        return pixarImageMapper.map(images)
    }

    override suspend fun saveImagesToDatabase(hits: Result<PixabayImage>) {
        hits.getOrNull()?.hits?.let { hitDao.insertAll(it) }
    }

    // Get all pixarImages from the database as a Flow object
    override fun getPixarImages() = flow {
        emit(hitDao.getAll())
    }

    // Get a pixarImage by its id from the database as a Flow object
    override fun getPixarImage(id: Int)= flow {
        emit(hitDao.get(id))
    }

    // Get all pixarImages whose tag contains a given string from the database as a Flow object
    override fun findPixarImagesByTag(tag: String) = flow {
        emit(hitDao.findByTag(tag))
    }

}