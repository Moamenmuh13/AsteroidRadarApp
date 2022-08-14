package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDataBase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.network.APIServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


class AsteroidRepository(private val dataBase: AsteroidDataBase) {
    val asteroid: LiveData<List<Asteroid>> =
        Transformations.map(dataBase.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }


    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val asteroids = APIServices.asteroidServices.getAsteroid(API_KEY)
            val result = parseAsteroidsJsonResult(JSONObject(asteroids))
            dataBase.asteroidDao.insertAsteroid(*result.asDatabaseModel())
        }
    }
}