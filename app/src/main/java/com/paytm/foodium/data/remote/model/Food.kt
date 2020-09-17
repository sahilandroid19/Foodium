package com.paytm.foodium.data.remote.model

import com.google.gson.annotations.SerializedName

data class Food (
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("title") val foodTitle: String,
    @SerializedName("body") val foodDescription: String,
    @SerializedName("imageUrl") val foodImage: String,
    @SerializedName("author") val foodAuthor: String
)