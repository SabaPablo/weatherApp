package com.example.saba.weatherapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.saba.weatherapp.R
import com.example.saba.weatherapp.model.Weather
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class WeathersAdapter : BaseAdapter {

    private var weatherList : ArrayList<Pair<String,Weather>>? = null
    private var context: Context? = null

    constructor(context: Context, weatherList:  ArrayList<Pair<String,Weather>>) : super() {
        this.weatherList = weatherList
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.weather_item, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }
        val pair = weatherList!![position]
        val inputDate = SimpleDateFormat("yyyy-MM-dd").parse(pair.first)
        val calendar = Calendar.getInstance()
        calendar.setTime(inputDate)
        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase()

        vh.temp.text = pair.second.main!!.temp_min!!.toInt().toString() + "° " + pair.second.main!!.temp_max!!.toInt().toString() + "°"
        vh.nameDay.text = dayOfWeek
        val icon = pair.second.weather!![0].icon
        Picasso.get()
                .load("http://openweathermap.org/img/w/$icon.png")
                .into(vh.imageView);


        return view
    }

    override fun getItem(position: Int): Any {
        return weatherList!![position]    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return weatherList!!.size
    }

    private class ViewHolder(view: View?) {
        val imageView: ImageView
        val nameDay: TextView
        val temp: TextView

        init {
            this.temp = view?.findViewById(R.id.temp_tv) as TextView
            this.nameDay = view?.findViewById(R.id.name_day_tv) as TextView
            this.imageView = view?.findViewById(R.id.icon_item_weather_iv)
        }
    }

}

