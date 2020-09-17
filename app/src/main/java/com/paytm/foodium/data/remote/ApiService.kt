package com.paytm.foodium.data.remote

import com.paytm.foodium.data.remote.model.Food
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/DummyFoodiumApi/api/posts/")
    fun getPosts(): Single<List<Food>>
}