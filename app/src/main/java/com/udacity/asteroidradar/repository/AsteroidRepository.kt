package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.database.AsteroidDataBase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.network.APIServices.asteroidServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AsteroidRepository(private val dataBase: AsteroidDataBase) {
    val asteroid: LiveData<List<Asteroid>> =
        Transformations.map(dataBase.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }


    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val asteroids = asteroidServices.getAsteroid(Constants.API_KEY)
            dataBase.asteroidDao.insertAsteroid()
        }
    }
}