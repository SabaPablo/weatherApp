package com.example.saba.weatherapp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.saba.weatherapp.R
import com.example.saba.weatherapp.adapters.WeathersAdapter
import com.example.saba.weatherapp.model.Weather
import com.example.saba.weatherapp.services.WeatherService
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() , MainView {

    private lateinit var mainPresenter: MainPresenter
    var weatherService:WeatherService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        mainPresenter.getWeatherWithIp()



    }

    override fun renderTemp(temp: Int?) {
        val tempV = findViewById<TextView>(R.id.temp_city_tv)
        tempV.text = "$temp°C"
    }

    override fun renderIcon(img: String) {
        val imageView = findViewById<ImageView>(R.id.icon_weather_im)
        Picasso.get()
                .load("http://openweathermap.org/img/w/$img.png")
                .resize(200, 200)
                .into(imageView);
    }

    override fun renderCityName(name: String?) {
        val city = findViewById<TextView>(R.id.city_name_tv)
        city.text = "$name"

    }

    override fun renderCountryAndProvince(region: String?, country: String?) {
        val country_tv = findViewById<TextView>(R.id.country_name_tv)
        country_tv.text = "$region, $country"
    }

    override fun renderStatusWeather(description: String?) {
        val status = findViewById<TextView>(R.id.status_weather_tv)
        status.text = description

    }

    override fun renderHumidity(humidity: Int?) {
        val humidity_tv = findViewById<TextView>(R.id.humidity_tv)
        humidity_tv.text = "$humidity%"}

    override fun renderMinAndMaxTemp(min: Int?, max: Int?) {
        val min_max_temp_tv = findViewById<TextView>(R.id.min_max_temp_tv)
        min_max_temp_tv.text = "$min° $max°"    }

    override fun velocityWind(speed: Int?) {
        val wind_speed_tv = findViewById<TextView>(R.id.wind_speed_tv)
        wind_speed_tv.text = "$speed m/s"    }

    override fun renderfiveDays(weathers: MutableMap<String, Weather>) {
        val listv = findViewById<ListView>(R.id.five_days_weather_lv)
        val list : ArrayList<Pair<String,Weather>> = ArrayList()
        weathers.forEach { (s, w) ->  list.add(Pair(s,w))}
        listv.adapter = WeathersAdapter(this,list)



    }

}
