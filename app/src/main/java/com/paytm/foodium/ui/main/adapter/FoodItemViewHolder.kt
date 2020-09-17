package com.paytm.foodium.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.paytm.foodium.R
import com.paytm.foodium.data.remote.model.Food
import com.paytm.foodium.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.item_food.view.*

class FoodItemViewHolder(
    parent: ViewGroup
) : BaseItemViewHolder<Food, FoodItemViewModel>(
    R.layout.item_food,
    parent
)  {

    override fun setUpObservers() {
        baseItemViewModel.itemData.observe(this, Observer {
            itemView.foodTitleView.text = it.foodTitle
            itemView.foodAuthorView.text = it.foodAuthor
            Glide.with(itemView.context).load(it.foodImage).into(itemView.foodImageView)
        })
    }

    override fun setUpView(view: View) {
        itemView.setOnClickListener {

        }
    }


}