package com.example.saba.weatherapp.views

import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.saba.weatherapp.R
import com.example.saba.weatherapp.services.WeatherService
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() , MainView {




    private lateinit var mainPresenter: MainPresenter
    var weatherService:WeatherService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        mainPresenter.getWeather(-42,-65)



    }

    override fun renderTemp(temp: Double?) {
        val tempV = findViewById<TextView>(R.id.temp_city_tv)
        tempV.setText("Temp: " + temp )
    }

    override fun renderIcon(img: String) {
        val imageView = findViewById<ImageView>(R.id.icon_weather_im)
        Picasso.get()
                .load("http://openweathermap.org/img/w/$img.png")
                .into(imageView);
    }

    override fun renderCityName(name: String?) {
        val city = findViewById<TextView>(R.id.city_name_tv)
        city.setText("Ciudad: " + name )

    }


}
