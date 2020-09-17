package com.paytm.foodium.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.paytm.foodium.utils.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    val viewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setActivityLayout())
        setUpObservers()
        setListeners()
        setView(savedInstanceState)
    }

    open fun setUpObservers() {
        viewModel.messageLiveData.observe(this, Observer {
            showMessage(it)
        })
    }

    open fun setActivityLayout(): Int {return 0}

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun setView(savedInstanceState: Bundle?) {}

    open fun setListeners() {}
}