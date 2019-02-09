package com.example.saba.weatherapp.services

import android.media.Image
import com.example.saba.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//weather?q=London

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeather(@Query("lat") lat:Double, @Query("lon") lon:Double) : Call<Weather>

    @GET("img/w/{icon}.png")
    fun getIcon(@Path("icon") icon: String?) : Call<Image>


}