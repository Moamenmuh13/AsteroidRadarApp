package com.udacity.asteroidradar.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.network.APIServices
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val repository = AsteroidRepository(database)


    private var _asteroidList = repository.asteroid
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidList


    private var _imgUrl = MutableLiveData<PictureOfDay>()
    val imgUrl: LiveData<PictureOfDay>
        get() = _imgUrl


    init {
        viewModelScope.launch {
            repository.refreshData()
            getPicOfDay()

        }

    }


    private suspend fun getPicOfDay() {
        withContext(Dispatchers.IO) {
            try {
                _imgUrl.postValue(APIServices.asteroidServices.getPicOfTheDay(API_KEY))
            } catch (e: Exception) {
                Toast.makeText(getApplication(), "Failed To load the img", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}

/**
 * Factory for constructing DevByteViewModel with parameter
 */



