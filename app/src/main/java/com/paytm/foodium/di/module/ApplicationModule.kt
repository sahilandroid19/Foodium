package com.paytm.foodium.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paytm.foodium.BuildConfig
import com.paytm.foodium.data.local.db.FoodDatabase
import com.paytm.foodium.data.local.db.dao.FoodDao
import com.paytm.foodium.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://patilshreyas.github.io/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideFoodDatabase(@ApplicationContext context: Context): FoodDatabase {
        return Room.databaseBuilder(context, FoodDatabase::class.java, "Foodium").build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(foodDatabase: FoodDatabase): FoodDao {
        return foodDatabase.foodDao()
    }
}