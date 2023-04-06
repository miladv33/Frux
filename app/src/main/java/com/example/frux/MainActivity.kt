package com.example.frux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frux.presentation.PixabayViewModel
import com.example.frux.ui.home.Home
import com.example.frux.ui.theme.FruxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val pixabayViewModel :PixabayViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themIsDark = pixabayViewModel.currentThemIsDark
            FruxTheme (darkTheme = themIsDark.value){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Home()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (pixabayViewModel.bottomSheetIsShowing.value) {
            pixabayViewModel.bottomSheetIsShowing.value = false
        } else {
            super.onBackPressed()
        }
    }
}