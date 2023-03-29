package com.example.frux.di

import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.frux.data.map.mappers.PixarImageMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Mapper {

    @Singleton
    @Provides
    fun providePixarImageMapper(failedMapperDelegateImpl: FailedMapperDelegateImpl): PixarImageMapper {
        return PixarImageMapper(failedMapperDelegateImpl)
    }
}