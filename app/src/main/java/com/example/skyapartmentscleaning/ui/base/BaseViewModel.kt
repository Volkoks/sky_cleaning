package com.example.skyapartmentscleaning.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel<T>(
    protected val liveDataToObserve: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            errorReturned(throwable)
        })

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun errorReturned(t: Throwable)

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
}