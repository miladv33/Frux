package com.example.frux.data.repository

import com.example.frux.data.local.dao.ThemeDao
import com.example.frux.data.model.Theme
import com.example.frux.data.repository.base.IBaseRepository
import com.example.frux.data.repository.safecall.SafeCallDelegate
import com.example.frux.data.repository.safecall.SafeCallDelegateImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class FruxThemeRepository @Inject constructor(
    private val themeDao: ThemeDao,
    private val safeCallDelegate: SafeCallDelegateImpl
) : IBaseRepository.FruxThemeRepository(), SafeCallDelegate by safeCallDelegate {
    override suspend fun saveImage(themeIsDark: Boolean) {
        themeDao.insertTheme(Theme(themeIsDark = themeIsDark))
    }
}