package com.paytm.foodium.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class FoodEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "body")
    var body: String? = null,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String? = null,

    @ColumnInfo(name = "author")
    var author: String? = null
)