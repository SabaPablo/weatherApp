package com.example.saba.weatherapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.saba.weatherapp.R
import com.example.saba.weatherapp.model.Weather
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class WeatherWorldAdapter(worldWeather: ArrayList<Weather>,context: Context): RecyclerView.Adapter<WeatherWorldAdapter.ViewHolder>() {

    var worldWeather : ArrayList<Weather>? = null
    var context : Context? = null

    init {
        this.worldWeather = worldWeather
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_card_view, parent, false))


    override fun getItemCount(): Int = this.worldWeather!!.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItems(worldWeather!![position])        }



    class ViewHolder (view: View?) : RecyclerView.ViewHolder(view) {

        fun bindItems(weather: Weather) {
            name_city.text= weather.name
            temp_city.text = weather.main!!.temp!!.toInt().toString() + "°"

            val icon = weather.weather!![0].icon
            Picasso.get()
                    .load("http://openweathermap.org/img/w/$icon.png")
                    .into(imageView)


        }

        val imageView: ImageView
        val name_city: TextView
        val temp_city: TextView

        init {
            this.temp_city = view?.findViewById(R.id.temp_city_tv) as TextView
            this.name_city = view?.findViewById(R.id.name_city_tv) as TextView
            this.imageView = view?.findViewById(R.id.icon_weather_iv)
        }


    }



}

