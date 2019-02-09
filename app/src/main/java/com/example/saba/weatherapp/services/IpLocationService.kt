package com.example.saba.weatherapp.services

import com.example.saba.weatherapp.model.Ubication
import com.example.saba.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IpLocationService {

    @GET("json/")
    fun getUbication() : Call<Ubication>
}