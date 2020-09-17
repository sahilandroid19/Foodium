package com.paytm.foodium.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paytm.foodium.data.local.db.dao.FoodDao
import com.paytm.foodium.data.local.db.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
}