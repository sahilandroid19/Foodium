package com.paytm.foodium.data.local.db.dao

import androidx.room.*
import com.paytm.foodium.data.local.db.entity.FoodEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.DELETE

@Dao
interface FoodDao {

    @Insert
    fun addFood(food: FoodEntity): Completable

    @Insert
    fun addFoodList(foodList: List<FoodEntity>): Completable

    @Query("SELECT * FROM foods")
    fun getFoodsList(): Single<List<FoodEntity>>

    @Query("DELETE FROM foods")
    fun deleteFoodList(): Completable
}