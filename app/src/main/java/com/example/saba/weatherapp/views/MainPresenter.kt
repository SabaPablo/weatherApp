package com.example.saba.weatherapp.views

import android.media.Image
import android.util.Log
import com.example.saba.weatherapp.application.AppAplication
import com.example.saba.weatherapp.model.Weather
import com.example.saba.weatherapp.services.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(mainView: MainView) {

    val mainView: MainView = mainView
    val service : WeatherService? = AppAplication.instance.weatherService;


    fun  getWeather(lat:Long, lon:Long) {
        var call = service!!.getWeather(lat,lon)
        call.enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                renderWeather(response!!.body()!!)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.v("retrofit", "call failed")
            }
        })
    }

    private fun renderWeather(weather: Weather) {
        mainView.renderTemp(weather.main!!.temp)
        mainView.renderCityName(weather.name)
        //searchIcon(weather.weather!!.icon)
        mainView.renderIcon(weather.weather!!.iterator().next().icon!!)
    }


}