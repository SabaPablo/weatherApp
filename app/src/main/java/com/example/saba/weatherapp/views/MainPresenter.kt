package com.example.saba.weatherapp.views

import android.util.Log
import com.example.saba.weatherapp.R
import com.example.saba.weatherapp.application.AppAplication
import com.example.saba.weatherapp.model.FiveDaysWeather
import com.example.saba.weatherapp.model.Ubication
import com.example.saba.weatherapp.model.Weather
import com.example.saba.weatherapp.services.IpLocationService
import com.example.saba.weatherapp.services.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

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
        if (ubication != null) {
            mainView.renderCountryAndProvince(ubication.region + ", " + ubication.country)
        }
        getWeather(ubication!!.latitude!!.toDouble(), ubication!!.longitude!!.toDouble(),null)
        getfiveDaysWeather(ubication!!.latitude!!.toDouble(), ubication!!.longitude!!.toDouble(),null)
    }

        private fun getfiveDaysWeather(lat: Double?, lon: Double?,city: String?) {
            var call = service!!.getFiveDaysWeather(lat,lon,city)
            call.enqueue(object  : Callback<FiveDaysWeather>{
                override fun onFailure(call: Call<FiveDaysWeather>, t: Throwable) {
                    Log.v("retrofit", "weather call failed")
                }

                override fun onResponse(call: Call<FiveDaysWeather>, response: Response<FiveDaysWeather>) {
                    renderFiveWeathers(response!!.body()!!)
                }
            })
        }

        private fun renderFiveWeathers(fiveDaysWeather: FiveDaysWeather) {
            val weathers : MutableMap<String,Weather> = mutableMapOf()
            fiveDaysWeather.list!!.iterator().forEach { w ->
                var aRange = w.dt_txt!!.removeRange(10,19)
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
        var call = service!!.getWeather(lat, lon, city)
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
        mainView.renderTemp(weather.main!!.temp!!.toInt())
        mainView.renderCityName(weather.name)
        mainView.renderIcon(weather.weather!!.iterator().next().icon!!)
        mainView.renderStatusWeather(weather.weather!!.get(0).description)
        mainView.renderHumidity(weather.main!!.humidity!!.toInt())
        mainView.renderMinAndMaxTemp(weather.main!!.temp_min!!.toInt(), weather.main!!.temp_max!!.toInt())
        mainView.velocityWind(weather.wind!!.speed!!.toInt())

    }

    fun getWeatherOf(city: String?) {
        if(city == null){
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
        var call = service!!.getWeather(null, null, ite.next())
        call.enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                res.add(response!!.body()!!)
                if(ite.hasNext())
                    callWeather(ite,res)
                else
                    renderWorldWeather(res)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.v("retrofit", "weather call failed")
            }
        })
    }

    private fun renderWorldWeather(res: MutableList<Weather>) {
        mainView.renderWorldWeather(res)
    }

    fun  getWeather(city: String?) {
        var call = service!!.getWeather(null, null, city)
        call.enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                renderWeather(response!!.body()!!)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.v("retrofit", "weather call failed")
            }
        })
    }

}