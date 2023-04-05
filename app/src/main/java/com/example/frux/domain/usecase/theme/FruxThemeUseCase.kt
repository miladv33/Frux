package com.example.frux.domain.usecase.theme

import com.example.frux.data.model.Theme

interface FruxThemeUseCase {
   suspend fun saveTheme(themIsDark: Boolean)
   suspend fun getTheme(): Theme?
}