package com.paytm.foodium.ui.base

import androidx.lifecycle.MutableLiveData
import com.paytm.foodium.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

abstract class BaseItemViewModel<T: Any> (
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
): BaseViewModel(compositeDisposable, networkHelper) {

    val itemData = MutableLiveData<T>()

    fun onManualCleared() = onCleared()

    fun updateData(data: T) {
        itemData.postValue(data)
    }

}