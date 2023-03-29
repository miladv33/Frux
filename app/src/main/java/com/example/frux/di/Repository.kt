package com.example.frux.di

import com.example.frux.data.local.dao.HitDao
import com.example.frux.data.map.mappers.PixarImageMapper
import com.example.frux.data.remote.service.PixabayService
import com.example.frux.data.repository.PixabayImageRepository
import com.example.frux.data.repository.base.IBaseRepository
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
        dao: HitDao
    ): IBaseRepository.PixabayImageRepository {
        return PixabayImageRepository(pixabayService, pixarImageMapper, dao)
    }
}