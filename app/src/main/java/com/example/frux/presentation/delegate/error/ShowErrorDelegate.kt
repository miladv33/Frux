package com.example.frux.presentation.delegate.error

import androidx.lifecycle.LiveData
import com.example.frux.data.model.Hit

interface ShowErrorDelegate {
    fun onFailure(throwable: Throwable)

    fun showDialog()

    fun hideDialog()

    fun getErrorDialogState():LiveData<Boolean>
}