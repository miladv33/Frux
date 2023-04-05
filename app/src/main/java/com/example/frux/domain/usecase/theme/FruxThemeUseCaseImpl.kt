package com.example.frux.domain.usecase.theme

import com.example.frux.data.model.Theme
import com.example.frux.data.repository.FruxThemeRepository
import javax.inject.Inject

class FruxThemeUseCaseImpl @Inject constructor(private val fruxRepository: FruxThemeRepository) : FruxThemeUseCase {
    override suspend fun saveTheme(themIsDark: Boolean) {
        fruxRepository.saveTheme(themIsDark)
    }

    override suspend fun getTheme(): Theme? {
        return fruxRepository.getTheme()
    }
}