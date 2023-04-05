package com.example.frux.di

import com.example.frux.data.local.dao.HitDao
import com.example.frux.data.local.dao.ThemeDao
import com.example.frux.data.map.mappers.PixarImageMapper
import com.example.frux.data.remote.service.PixabayService
import com.example.frux.data.repository.FruxThemeRepository
import com.example.frux.data.repository.PixabayImageRepository
import com.example.frux.data.repository.base.IBaseRepository
import com.example.frux.data.repository.safecall.SafeCallDelegateImpl
import com.example.frux.domain.usecase.theme.FruxThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Repository {
    @Singleton
    @Provides
    fun providePixabayRepository(
        pixabayService: PixabayService,
        pixarImageMapper: PixarImageMapper,
        dao: HitDao,
        safeCallDelegate: SafeCallDelegateImpl
    ): IBaseRepository.PixabayImageRepository {
        return PixabayImageRepository(pixabayService, pixarImageMapper, dao, safeCallDelegate)
    }

    @Singleton
    @Provides
    fun provideFruxThemeRepository(
        themeDao: ThemeDao,
        safeCallDelegate: SafeCallDelegateImpl
    ): FruxThemeRepository {
        return FruxThemeRepository(themeDao, safeCallDelegate)
    }
}