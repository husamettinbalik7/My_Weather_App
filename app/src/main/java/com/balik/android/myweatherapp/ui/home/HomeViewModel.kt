package com.balik.android.myweatherapp.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balik.android.myweatherapp.model.WeatherResponse
import com.balik.android.myweatherapp.repository.WeatherRepository

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {


    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData


    fun getDataFromService() {
        weatherRepository.getDataFromService {
       _weatherData.postValue(it)
}
    }
}