package com.paytm.foodium.ui.base

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paytm.foodium.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel @ViewModelInject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val messageLiveData = MutableLiveData<String>()

    protected fun checkInternetConnection(): Boolean {
        return if(networkHelper.isNetworkConnected()) {
            true
        } else {
            messageLiveData.postValue("No Network Available")
            false
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}