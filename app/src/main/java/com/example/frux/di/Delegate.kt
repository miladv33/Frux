package com.example.frux.di

import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.frux.presentation.delegate.error.ShowDialogDelegateImpl
import com.example.frux.presentation.delegate.error.ShowErrorDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Delegate {
    @Provides
    fun provideFailedMapperDelegateImpl(): FailedMapperDelegateImpl {
        return FailedMapperDelegateImpl()
    }

    @Provides
    fun provideShowErrorDelegate(): ShowErrorDelegate {
        return ShowDialogDelegateImpl()
    }
}