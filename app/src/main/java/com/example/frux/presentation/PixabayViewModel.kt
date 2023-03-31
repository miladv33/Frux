package com.example.frux.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frux.data.model.Hit
import com.example.frux.data.model.PixabayImage
import com.example.frux.domain.usecase.pixabayimage.PixabayUseCase
import com.example.frux.presentation.delegate.error.ShowErrorDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel @Inject constructor(
    private val pixabayUseCase: PixabayUseCase,
    private val showErrorDelegate: ShowErrorDelegate
) : ViewModel(), ShowErrorDelegate by showErrorDelegate {
    private val _pixabayImageLiveData = MutableLiveData<PixabayImage>()
    val pixabayImageLiveData: LiveData<PixabayImage> = _pixabayImageLiveData
    val selectedImage:MutableState<Hit?> = mutableStateOf(null)


    fun searchImage(searchKey: String, imageType: String) {
        viewModelScope.launch {
            _pixabayImageLiveData.value = null
            pixabayUseCase.searchImage(searchKey, imageType).flowOn(Dispatchers.IO).take(2)
                .collect {
                    it.onSuccess { searchedImage ->
                        _pixabayImageLiveData.value = searchedImage
                    }
                    it.onFailure { throwable ->
                        onFailure(throwable)
                    }
                }
        }
    }

    fun setSelectedImage(it: Hit) {
        selectedImage.value = it
    }
}