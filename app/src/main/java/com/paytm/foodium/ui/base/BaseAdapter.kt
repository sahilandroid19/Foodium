package com.paytm.foodium.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, VH : BaseItemViewHolder<T, out BaseItemViewModel<T>>>(
    parentLifecycle: Lifecycle,
    private var dataList: List<T>
) : RecyclerView.Adapter<VH>() {

    init {
        parentLifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                recyclerView?.run {
                    for (i in 0..childCount) {
                        getChildAt(i).let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).run {
                                onDestroy()
                                onManualCleared()
                            }
                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStart() {
                recyclerView?.run {
                    if (layoutManager is LinearLayoutManager) {
                        val first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        val last = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        for (i in first..last) {
                            (findViewHolderForAdapterPosition(i) as BaseItemViewHolder<*, *>?)?.onStart()
                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStop() {
                recyclerView?.run {
                    for (i in 0..childCount) {
                        getChildAt(i).let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).onStop()
                        }
                    }
                }
            }
        })
    }

    var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onStart()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onStop()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun updateData(dataList: List<T>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}