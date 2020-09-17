package com.paytm.foodium.ui.main.adapter

import androidx.hilt.lifecycle.ViewModelInject
import com.paytm.foodium.data.remote.model.Food
import com.paytm.foodium.ui.base.BaseItemViewModel
import com.paytm.foodium.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class FoodItemViewModel @ViewModelInject constructor(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Food>(compositeDisposable, networkHelper) {

    fun getPosts() {

    }

}