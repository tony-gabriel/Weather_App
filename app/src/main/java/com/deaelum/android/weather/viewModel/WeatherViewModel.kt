package com.deaelum.android.weather.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deaelum.android.weather.BuildConfig
import com.deaelum.android.weather.dataModel.WeatherModel
import com.deaelum.android.weather.network.NetworkResponse
import com.deaelum.android.weather.network.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val  weatherApi = RetrofitInstance.weatherApi
    private val _weather = MutableLiveData< NetworkResponse<WeatherModel>>()
    val weather: LiveData<NetworkResponse<WeatherModel>> = _weather

    fun getData(city: String){
        _weather.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(BuildConfig.API_KEY, city)

                if (response.isSuccessful){
                    response.body()?.let {
                        _weather.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weather.value = NetworkResponse.Error("Failed to load data")
                }
            }catch (e: Exception){
                _weather.value = NetworkResponse.Error("Failed to load data")
            }
        }

    }
}