package com.example.frux.domain.usecase.theme

interface FruxThemeUseCase {
   suspend fun saveTheme(themIsDark: Boolean)
}