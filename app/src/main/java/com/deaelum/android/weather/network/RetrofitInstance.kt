package com.deaelum.android.weather.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var baseUrl = "https://api.weatherapi.com"

    private fun getInstance(): Retrofit{
       return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val weatherApi: WeatherApi = getInstance().create(WeatherApi::class.java)
}