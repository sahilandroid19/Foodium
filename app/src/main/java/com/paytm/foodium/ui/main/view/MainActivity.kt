package com.paytm.foodium.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.paytm.foodium.R
import com.paytm.foodium.data.remote.model.Food
import com.paytm.foodium.data.remote.model.Response
import com.paytm.foodium.ui.base.BaseActivity
import com.paytm.foodium.ui.base.BaseViewModel
import com.paytm.foodium.ui.main.adapter.FoodAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var foodAdapter: FoodAdapter

    override fun setActivityLayout(): Int = R.layout.activity_main

    override fun setView(savedInstanceState: Bundle?) {
        mainViewModel.getPost()

    }

    override fun setListeners() {

    }

    override fun setUpObservers() {
        super.setUpObservers()


    }

    private fun second() {
        Log.v("sahil", "Second")
    }
}