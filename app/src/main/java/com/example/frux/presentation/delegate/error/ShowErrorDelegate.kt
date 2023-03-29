package com.example.frux.presentation.delegate.error

import androidx.lifecycle.LiveData

interface ShowErrorDelegate {
    fun onFailure(throwable: Throwable)

    fun showDialog()

    fun hideDialog()

    fun getErrorDialogState():LiveData<Boolean>
}