package com.paytm.foodium.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.paytm.foodium.R
import javax.inject.Inject

abstract class BaseItemViewHolder<T: Any, VM: BaseItemViewModel<T>>(
    @LayoutRes private val layoutRes: Int,
    private val parent: ViewGroup
): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)),
    LifecycleOwner{

    init {
        onCreate()
    }

    @Inject
    lateinit var baseItemViewModel: VM

    @Inject
    lateinit var lifeCycleRegistry: LifecycleRegistry

    private fun onCreate() {
        setUpObservers()
        setUpObservers()
        setUpView(itemView)
        lifecycle.currentState = Lifecycle.State.INITIALIZED
        lifecycle.currentState = Lifecycle.State.CREATED
    }

    fun onStop() {
        lifecycle.currentState = Lifecycle.State.STARTED
        lifecycle.currentState = Lifecycle.State.CREATED
    }

    fun onStart() {
        lifecycle.currentState = Lifecycle.State.STARTED
        lifecycle.currentState = Lifecycle.State.RESUMED
    }

    fun onDestroy() {
        lifecycle.currentState = Lifecycle.State.DESTROYED
    }

    fun bind(data: T) {
        baseItemViewModel.updateData(data)
    }

    abstract fun setUpObservers()

    abstract fun setUpView(view: View)

    fun onManualCleared() = baseItemViewModel.onManualCleared()

    override fun getLifecycle() = lifeCycleRegistry
}