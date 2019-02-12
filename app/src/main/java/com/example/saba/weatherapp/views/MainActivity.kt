package com.example.saba.weatherapp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.WindowManager
import android.widget.*
import com.example.saba.weatherapp.R
import com.example.saba.weatherapp.adapters.WeatherWorldAdapter
import com.example.saba.weatherapp.adapters.WeathersAdapter
import com.example.saba.weatherapp.model.Weather
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() , MainView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        mainPresenter = MainPresenter(this)

        mainPresenter.getWeatherOf(null)
        mainPresenter.getWeathersDefaults()

        val searchEdit = findViewById<EditText>(R.id.city_search_tv)
        val searchBtn = findViewById<ImageButton>(R.id.search_btn)
        searchBtn.setOnClickListener {
            mainPresenter.getWeatherOf(searchEdit.text.toString())
        }


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
                .into(imageView)
    }

    override fun renderCityName(name: String?) {
        val city = findViewById<TextView>(R.id.city_name_tv)
        city.text = "$name"

    }

    override fun renderCountryAndProvince(region: String?) {
        val countryNameTextView = findViewById<TextView>(R.id.country_name_tv)
        countryNameTextView.text = "$region"
    }

    override fun renderStatusWeather(description: String?) {
        val status = findViewById<TextView>(R.id.status_weather_tv)
        status.text = description

    }

    override fun renderHumidity(humidity: Int?) {
        val humidityTextView = findViewById<TextView>(R.id.humidity_tv)
        humidityTextView.text = "$humidity%"}

    override fun renderMinAndMaxTemp(min: Int?, max: Int?) {
        val maxMinTextView = findViewById<TextView>(R.id.min_max_temp_tv)
        maxMinTextView.text = "$min°/$max°"    }

    override fun velocityWind(speed: Int?) {
        val windSpeedTextView = findViewById<TextView>(R.id.wind_speed_tv)
        windSpeedTextView.text = "$speed m/s"    }

    override fun renderfiveDays(weathers: MutableMap<String, Weather>) {
        val listv = findViewById<ListView>(R.id.five_days_weather_lv)
        val list : ArrayList<Pair<String,Weather>> = ArrayList()
        weathers.forEach { (s, w) ->  list.add(Pair(s,w))}
        listv.adapter = WeathersAdapter(this,list)

    }
    override fun renderWorldWeather(res: MutableList<Weather>) {
        val listv = findViewById<RecyclerView>(R.id.weather_world_rv)
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        listv.layoutManager = linearLayoutManager
        listv.adapter = WeatherWorldAdapter(ArrayList(res), this)
    }
    override fun setErrorCityNotFound() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se encontro la ciudad seleccionada").show()
    }

    override fun setErrorServerNotFound() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No con el servidor").show()    }

    override fun setAnyError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Ocurrio un error inesperado").show()
   }



}
