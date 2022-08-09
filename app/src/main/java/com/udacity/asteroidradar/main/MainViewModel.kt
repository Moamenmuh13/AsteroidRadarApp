package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.network.APIServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val _imgUrl = MutableLiveData<PictureOfDay>()
    val imgUrl: LiveData<PictureOfDay>
        get() = _imgUrl

    init {
        viewModelScope.launch {
        }
    }


}
