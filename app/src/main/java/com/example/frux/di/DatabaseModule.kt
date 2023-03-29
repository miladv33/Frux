package com.example.frux.di

import android.content.Context
import com.example.frux.data.local.HitDatabase
import com.example.frux.data.local.dao.HitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): HitDatabase {
        return HitDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideHitDAO(hitDatabase: HitDatabase): HitDao {
        return hitDatabase.hitDao()
    }

}