package com.example.saba.weatherapp.application

import android.app.Application
import com.example.saba.weatherapp.services.WeatherInterceptor
import com.example.saba.weatherapp.services.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppAplication : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        weatherService = retrofit.create(WeatherService::class.java)
    }


    companion object {
        lateinit var instance: AppAplication
            private set
    }



    val apiClient = OkHttpClient.Builder().addInterceptor(WeatherInterceptor()).build()


    var weatherService: WeatherService? = null
    var retrofit = Retrofit.Builder().apply{
            baseUrl("https://api.openweathermap.org/").
            addConverterFactory(GsonConverterFactory.create())
            client(apiClient)
        }.build()




    /**
     *
     * bibliografia:
     * https://stackoverflow.com/questions/37391221/kotlin-singleton-application-class
     * https://square.github.io/retrofit/
     */




}
