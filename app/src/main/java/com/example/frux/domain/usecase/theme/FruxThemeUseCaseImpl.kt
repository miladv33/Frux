package com.example.frux.domain.usecase.theme

import com.example.frux.data.repository.FruxThemeRepository
import javax.inject.Inject

class FruxThemeUseCaseImpl @Inject constructor(private val fruxRepository: FruxThemeRepository) : FruxThemeUseCase {
    override suspend fun saveTheme(themIsDark: Boolean) {
        fruxRepository.saveImage(themIsDark)
    }
}