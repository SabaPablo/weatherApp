package com.example.saba.weatherapp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.saba.weatherapp.R
import com.example.saba.weatherapp.services.WeatherService

class MainActivity : AppCompatActivity() , MainView {

    private lateinit var mainPresenter: MainPresenter
    var weatherService:WeatherService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        mainPresenter.getWeather(-42,-65)


    }
}
