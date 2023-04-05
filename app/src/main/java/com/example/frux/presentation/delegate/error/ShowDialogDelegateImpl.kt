package com.example.frux.presentation.delegate.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.frux.data.enum.Error
import com.example.frux.data.model.base.CustomException

class ShowDialogDelegateImpl : ShowErrorDelegate {
    /**
     * To observe dialog state on the UI, we need a liveData
     */
    private val _showErrorDialogLiveData = MutableLiveData<Boolean>()
    private val showErrorDialogLiveData: LiveData<Boolean> = _showErrorDialogLiveData
    private var isErrorState:Boolean = false

    /**
     * A liveData can be observed by the UI if more details are needed about an exception.
     */
    private val _errorLiveData = MutableLiveData<Throwable>()
    override fun onFailure(throwable: Throwable) {
        setErrorState(true)
        showDialog()
        if (throwable is CustomException) {
            when (throwable.error) {
                Error.NullObject -> {
                    _errorLiveData.value = throwable
                }
            }
        } else {
            _errorLiveData.value = throwable
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
    override fun getErrorMessage(): String {
        return _errorLiveData.value?.message ?: ""
    }

    override fun isErrorState(): Boolean {
        return isErrorState
    }

    override fun setErrorState(boolean: Boolean) {
        isErrorState = boolean
    }

}