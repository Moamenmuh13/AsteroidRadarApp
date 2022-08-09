package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidServices {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroid(
        @Query("api_key") api_key: String
    ): List<Asteroid>

    @GET("planetary/apod")
    suspend fun getPicOfTheDay(
        @Query("api_key") api_key: String
    ): PictureOfDay
}

object APIServices {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val asteroidServices: AsteroidServices by lazy {
        retrofit.create(AsteroidServices::class.java)
    }
}
