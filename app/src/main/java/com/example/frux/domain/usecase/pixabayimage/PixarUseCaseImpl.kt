package com.example.frux.domain.usecase.pixabayimage

import com.example.frux.data.repository.base.IBaseRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class PixarUseCaseImpl @Inject constructor(private val pixabayImageRepository: IBaseRepository.PixabayImageRepository) :
    PixabayUseCase {
    override fun searchImage(searchKey: String, imageType: String) = flow {
        // Try to retrieve data from the local database first
        val localData = pixabayImageRepository.findPixarImagesByTag(searchKey).firstOrNull()
        if (!localData?.hits.isNullOrEmpty()) {
            emit(Result.success(localData))
        } else {
            // If there is no local data, retrieve data from the server
            val searchImage = pixabayImageRepository.searchImage(searchKey, imageType = imageType)
            emit(searchImage)
        }
    }
}
