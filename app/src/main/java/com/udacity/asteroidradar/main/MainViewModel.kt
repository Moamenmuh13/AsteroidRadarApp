package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.udacity.asteroidradar.AsteroidApplication
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.database.AsteroidDataBase
import com.udacity.asteroidradar.database.DatabaseAsteroid
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.network.APIServices
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val repository = AsteroidRepository(database)



    init {

    }

}
