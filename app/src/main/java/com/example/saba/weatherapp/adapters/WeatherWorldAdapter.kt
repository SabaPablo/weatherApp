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

class WeatherWorldAdapter(worldWeather: ArrayList<Weather>, private val listener: (Int) -> Unit): RecyclerView.Adapter<WeatherWorldAdapter.ViewHolder>() {

    private var worldWeather : ArrayList<Weather> = worldWeather

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_card_view, parent, false))


    override fun getItemCount(): Int = this.worldWeather!!.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItems(worldWeather[position],position, listener)
       holder.setIsRecyclable(false);}



    class ViewHolder (view: View?) : RecyclerView.ViewHolder(view) {

        fun bindItems(weather: Weather,pos: Int, listener: (Int) -> Unit) = with(itemView) {
            nameCity.text= weather.name
            tempCity.text = weather.main!!.temp!!.toInt().toString() + "°"

            val icon = weather.weather!![0].icon
            Picasso.get()
                    .load("http://openweathermap.org/img/w/$icon.png")
                    .into(imageView)

            nameCity.setOnClickListener {
                listener(pos)}

        }

        private val imageView: ImageView = view?.findViewById(R.id.icon_weather_iv)!!
        private val nameCity: TextView = view?.findViewById(R.id.name_city_tv) as TextView
        private val tempCity: TextView = view?.findViewById(R.id.temp_city_tv) as TextView

    }


    }



