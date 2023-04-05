package com.example.frux.di

import com.example.frux.data.repository.FruxThemeRepository
import com.example.frux.data.repository.base.IBaseRepository
import com.example.frux.domain.usecase.pixabayimage.PixabayUseCase
import com.example.frux.domain.usecase.pixabayimage.PixarUseCaseImpl
import com.example.frux.domain.usecase.theme.FruxThemeUseCase
import com.example.frux.domain.usecase.theme.FruxThemeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCase {

    @Singleton
    @Provides
    fun providePixabayUseCase(pixabayImageRepository: IBaseRepository.PixabayImageRepository): PixabayUseCase {
        return PixarUseCaseImpl(pixabayImageRepository)
    }

    @Singleton
    @Provides
    fun provideFruxThemeUseCase(fruxRepository: FruxThemeRepository): FruxThemeUseCase {
        return FruxThemeUseCaseImpl(fruxRepository)
    }
}