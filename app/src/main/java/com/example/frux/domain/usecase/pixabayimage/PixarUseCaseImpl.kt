package com.example.frux.domain.usecase.pixabayimage

import com.example.frux.data.repository.base.IBaseRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class PixarUseCaseImpl @Inject constructor(private val pixabayImageRepository: IBaseRepository.PixabayImageRepository) :
    PixabayUseCase {
    override fun searchImage(searchKey: String, imageType: String) = flow {
        val searchImage = pixabayImageRepository.searchImage(searchKey, imageType = imageType)
        emit(searchImage)
    }
}