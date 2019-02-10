package com.example.saba.weatherapp.services

import com.example.saba.weatherapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class WeatherInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url().newBuilder().addQueryParameter("APPID", "5397116c233ba706df52bcd198eb196b").
                addQueryParameter("mode", "json").
                addQueryParameter("units", "metric")
                .addQueryParameter("lang","es").build()
        return chain.proceed(chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build())
    }

}