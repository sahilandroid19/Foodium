package com.paytm.foodium.ui.main.adapter

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.paytm.foodium.data.remote.model.Food
import com.paytm.foodium.ui.base.BaseAdapter
import javax.inject.Inject

class FoodAdapter(
    dataList: List<Food>,
    parentLifecycle: Lifecycle
) : BaseAdapter<Food, FoodItemViewHolder>(parentLifecycle, dataList) {

    @Inject
    lateinit var foodItemViewModel: FoodItemViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder =
        FoodItemViewHolder(parent)
}