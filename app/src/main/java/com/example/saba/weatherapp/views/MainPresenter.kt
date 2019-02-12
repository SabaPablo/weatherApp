package com.example.saba.weatherapp.views

import android.util.Log
import com.example.saba.weatherapp.application.AppAplication
import com.example.saba.weatherapp.model.FiveDaysWeather
import com.example.saba.weatherapp.model.Location
import com.example.saba.weatherapp.model.Weather
import com.example.saba.weatherapp.services.IpLocationService
import com.example.saba.weatherapp.services.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(mainView: MainView) {

    val mainView: MainView = mainView
    private val service : WeatherService? = AppAplication.instance.weatherService
    private val ipLocationService : IpLocationService? = AppAplication.instance.ipLocationService


    fun getWeatherWithIp(){
        val call = ipLocationService!!.getLocation()
        call.enqueue(object : Callback<Location>{
            override fun onFailure(call: Call<Location>, t: Throwable) {
                failRetrofit()
            }

            override fun onResponse(call: Call<Location>, response: Response<Location>) {
                getWeatherByLocation(response.body())

            }

        })
    }

    private fun getWeatherByLocation(location: Location?) {
        if (location != null) {
            mainView.renderCountryAndProvince(location.region + ", " + location.country)
        }
        getWeather(location!!.latitude!!.toDouble(), location.longitude!!.toDouble(),null)
        getfiveDaysWeather(location.latitude!!.toDouble(), location.longitude!!.toDouble(),null)
    }

        private fun getfiveDaysWeather(lat: Double?, lon: Double?,city: String?) {
            val call = service!!.getFiveDaysWeather(lat,lon,city)
            call.enqueue(object  : Callback<FiveDaysWeather>{
                override fun onFailure(call: Call<FiveDaysWeather>, t: Throwable) {
                    failRetrofit()
                }

                override fun onResponse(call: Call<FiveDaysWeather>, response: Response<FiveDaysWeather>) {
                    if (response.isSuccessful) {
                        renderFiveWeathers(response.body()!!)
                    } else {
                        handleError(response)

                    }

                }
            })
        }

    private fun <T> handleError(response: Response<T>) {
        when (response.code()) {
            404 -> mainView.setErrorCityNotFound()
            500 -> mainView.setErrorServerNotFound()
            else -> mainView.setAnyError()
        }
    }

    private fun renderFiveWeathers(fiveDaysWeather: FiveDaysWeather) {
            val weathers : MutableMap<String,Weather> = mutableMapOf()
            fiveDaysWeather.list!!.iterator().forEach { w ->
                val aRange = w.dt_txt!!.removeRange(10,19)
                if(!weathers.containsKey(aRange))
                    weathers[aRange] = Weather()
                if(weathers[aRange]!!.main == null){
                    weathers[aRange]!!.main = w.main
                    weathers[aRange]!!.weather = w.weather
                }else{
                    weathers[aRange]!!.main!!.temp_min = minOf(weathers[aRange]!!.main!!.temp_min!!, w.main!!.temp_min!!)
                    weathers[aRange]!!.main!!.temp_max = maxOf(weathers[aRange]!!.main!!.temp_max!!, w.main!!.temp_max!!)

                }
            }

            mainView.renderfiveDays(weathers)
        }

    fun  getWeather(lat: Double?, lon: Double?, city: String?) {
        val call = service!!.getWeather(lat, lon, city)
        call.enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    renderWeather(response.body()!!)
                } else {
                    handleError(response)
                }
            }
            override fun onFailure(call: Call<Weather>,     t: Throwable) {
               failRetrofit()
            }
        })
    }

    private fun failRetrofit() {
        Log.v("retrofit", "weather call failed")
        mainView.setErrorAPI()
    }

    private fun renderWeather(weather: Weather) {
        mainView.renderTemp(weather.main!!.temp!!.toInt())
        mainView.renderCityName(weather.name)
        mainView.renderIcon(weather.weather!!.iterator().next().icon!!)
        mainView.renderStatusWeather(weather.weather!!.get(0).description)
        mainView.renderHumidity(weather.main!!.humidity!!.toInt())
        mainView.renderMinAndMaxTemp(weather.main!!.temp_min!!.toInt(), weather.main!!.temp_max!!.toInt())
        mainView.velocityWind(weather.wind!!.speed!!.toInt())

    }

    fun getWeatherOf(city: String?) {
        if(city == null || city.isEmpty()){
            getWeatherWithIp()
        }else{
            getWeather(null,null, city)
            getfiveDaysWeather(null,null,city)
            mainView.renderCountryAndProvince("")


        }
    }

    fun getWeathersDefaults() {
        val strings = hashSetOf("Madrid", "Paris", "Nueva York", "Tokio", "Roma")
        val res = mutableListOf<Weather>()
        val ite = strings.iterator()
       callWeather(ite,res)
    }

    private fun callWeather(ite: MutableIterator<String>, res: MutableList<Weather>) {
        val call = service!!.getWeather(null, null, ite.next())
        call.enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                res.add(response.body()!!)
                if(ite.hasNext())
                    callWeather(ite,res)
                else
                    renderWorldWeather(res)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                failRetrofit()
            }
        })
    }

    private fun renderWorldWeather(res: MutableList<Weather>) {
        mainView.renderWorldWeather(res)
    }

}