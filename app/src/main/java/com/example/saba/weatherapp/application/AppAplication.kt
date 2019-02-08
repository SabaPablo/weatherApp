package com.example.saba.weatherapp.application

import android.app.Application
import android.util.Log

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.saba.weatherapp.services.WeatherService

class AppAplication : Application() {

    var weatherService: WeatherService = WeatherService();

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        lateinit var instance: AppAplication
            private set
    }


    /**
     *
     * bibliografia:
     * https://stackoverflow.com/questions/37391221/kotlin-singleton-application-class
     */




}
