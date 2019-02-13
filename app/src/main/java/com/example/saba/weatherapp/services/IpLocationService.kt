package com.example.saba.weatherapp.services

import com.example.saba.weatherapp.model.Location
import retrofit2.Call
import retrofit2.http.GET

interface IpLocationService {

    @GET("json/?lang=es")
    fun getLocation() : Call<Location>
}