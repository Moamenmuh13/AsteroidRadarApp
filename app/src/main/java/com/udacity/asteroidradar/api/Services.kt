package com.udacity.asteroidradar.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.PictureOfDay
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface AsteroidServices {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroid(
        @Query("api_key") api_key: String,
    ): String

    @GET("planetary/apod")
    suspend fun getPicOfTheDay(
        @Query("api_key") api_key: String,
    ): PictureOfDay
}

object APIServices {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()



    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val asteroidServices: AsteroidServices by lazy {
        retrofit.create(AsteroidServices::class.java)
    }
}
