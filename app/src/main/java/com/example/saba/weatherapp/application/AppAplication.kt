package com.example.saba.weatherapp.application

import android.app.Application
import com.example.saba.weatherapp.services.IpLocationService
import com.example.saba.weatherapp.services.WeatherInterceptor
import com.example.saba.weatherapp.services.WeatherService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppAplication : Application() {





    val apiClient = OkHttpClient.Builder().addInterceptor(WeatherInterceptor()).build()


    var weatherService: WeatherService? = null

    var ipLocationService: IpLocationService? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        var retrofitOpenWeather = Retrofit.Builder().apply{
            baseUrl("https://api.openweathermap.org/").
                    addConverterFactory(GsonConverterFactory.create())
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(apiClient)
        }.build()
        weatherService = retrofitOpenWeather.create(WeatherService::class.java)

        var retrofitIpLocationService = Retrofit.Builder().apply{
            addConverterFactory(GsonConverterFactory.create())
            baseUrl("http://free.ipwhois.io/")
        }.build()
        ipLocationService = retrofitIpLocationService.create(IpLocationService::class.java)
    }


    companion object {
        lateinit var instance: AppAplication
            private set
    }


    /**
     *
     * bibliografia:
     * https://stackoverflow.com/questions/37391221/kotlin-singleton-application-class
     * https://square.github.io/retrofit/
     */




}
