package com.example.saba.weatherapp.services

import com.example.saba.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//weather?q=London

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeather(@Query("lat") lat:Long, @Query("lon") lon:Long) : Call<Weather>




}