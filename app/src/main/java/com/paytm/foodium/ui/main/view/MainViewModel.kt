package com.paytm.foodium.ui.main.view

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.paytm.foodium.data.local.db.entity.FoodEntity
import com.paytm.foodium.data.remote.model.Food
import com.paytm.foodium.data.remote.model.Response
import com.paytm.foodium.data.remote.repository.MainDataRepository
import com.paytm.foodium.ui.base.BaseViewModel
import com.paytm.foodium.utils.NetworkHelper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainViewModel @ViewModelInject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper,
    private val mainDataRepository: MainDataRepository
) : BaseViewModel(compositeDisposable, networkHelper) {

    val foodPosts = MutableLiveData<Response<List<Food>>>()

    fun getPost() {
        deleteDatabase()

        compositeDisposable.add(
            mainDataRepository.getFoodFromApi()
                .doOnSuccess {
                    saveInDatabase(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>() {

                    override fun onSuccess(t: List<Food>) {
                        Log.v("sahil", "API ${System.currentTimeMillis()}")
                        foodPosts.postValue(Response.Success(t))
                    }

                    override fun onError(e: Throwable) {
                        foodPosts.postValue(Response.Error(Exception(e)))
                    }
                }))
    }

    private fun saveInDatabase(foodList: List<Food>) {
        compositeDisposable.add(
            Observable.fromIterable(foodList)
                .map {
                    return@map FoodEntity(title = it.foodTitle, body = it.foodDescription, imageUrl = it.foodImage, author = it.foodAuthor)
                }
                .toList()
                .flatMapCompletable {
                    Log.v("sahil", "Database ${System.currentTimeMillis()}")
                    mainDataRepository.saveFoodListInDb(it).subscribeOn(Schedulers.io())
                }
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    private fun deleteDatabase() {
    }
}