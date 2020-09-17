package com.paytm.foodium.data.remote.repository

import com.paytm.foodium.data.local.db.dao.FoodDao
import com.paytm.foodium.data.local.db.entity.FoodEntity
import com.paytm.foodium.data.remote.ApiService
import com.paytm.foodium.data.remote.model.Food
import com.paytm.foodium.data.remote.model.Response
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class MainDataRepository @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val apiService: ApiService,
    private val foodDao: FoodDao
) {

    fun getFoodFromApi(): Single<List<Food>> = apiService.getPosts()

    fun saveFoodInDb(foodEntity: FoodEntity): Completable = foodDao.addFood(foodEntity)

    fun saveFoodListInDb(foodList: List<FoodEntity>): Completable = foodDao.addFoodList(foodList)

    fun getFoodFromDatabase(): Single<List<FoodEntity>> = foodDao.getFoodsList()

    fun deleteFoodListFromDatabase() = foodDao.deleteFoodList()

}