package com.example.frux.presentation.delegate.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.frux.data.model.base.CustomException
import com.example.frux.data.enum.Error

class ShowDialogDelegateImpl : ShowErrorDelegate {
    /**
     * To observe dialog state on the UI, we need a liveData
     */
    private val _showErrorDialogLiveData = MutableLiveData<Boolean>()
    val showErrorDialogLiveData: LiveData<Boolean> = _showErrorDialogLiveData

    /**
     * A liveData can be observed by the UI if more details are needed about an exception.
     */
    private val _randomQuoteErrorLiveData = MutableLiveData<Throwable>()

    override fun onFailure(throwable: Throwable) {
        showDialog()
        if (throwable is CustomException) {
            when (throwable.error) {
                Error.NullObject -> {
                    _randomQuoteErrorLiveData.value = throwable
                }
            }
        } else {
            _randomQuoteErrorLiveData.value = throwable
        }
    }

    override fun showDialog() {
        _showErrorDialogLiveData.value = true
    }

    override fun hideDialog() {
        _showErrorDialogLiveData.value = false
    }

    override fun getErrorDialogState(): LiveData<Boolean> {
        return showErrorDialogLiveData
    }

}