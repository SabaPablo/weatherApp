package com.example.saba.weatherapp.views

import android.util.Log
import com.example.saba.weatherapp.application.AppAplication
import com.example.saba.weatherapp.model.Ubication
import com.example.saba.weatherapp.model.Weather
import com.example.saba.weatherapp.services.IpLocationService
import com.example.saba.weatherapp.services.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(mainView: MainView) {

    val mainView: MainView = mainView
    val service : WeatherService? = AppAplication.instance.weatherService;
    val ipLocationService : IpLocationService? = AppAplication.instance.ipLocationService;


    fun getWeatherWithIp(){
        var call = ipLocationService!!.getUbication()
        call.enqueue(object : Callback<Ubication>{
            override fun onFailure(call: Call<Ubication>, t: Throwable) {
                Log.v("retrofit", "ip ubication call failed")
            }

            override fun onResponse(call: Call<Ubication>, response: Response<Ubication>) {
                getWeatherByUbication(response.body())

            }

        })
    }

    private fun getWeatherByUbication(ubication: Ubication?) {
        getWeather(ubication!!.latitude!!.toDouble(), ubication!!.longitude!!.toDouble())
    }

    fun  getWeather(lat: Double?, lon: Double?) {
        var call = service!!.getWeather(lat!!, lon!!)
        call.enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                renderWeather(response!!.body()!!)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.v("retrofit", "weather call failed")
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