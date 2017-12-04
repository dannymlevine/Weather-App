package com.levine.daniel.weatherapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by daniel on 11/17/17.
 */
interface WeatherAPI  {
    @GET("yql?format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    fun getForcast(@Query("q") q: String) : Call<Weather>

}

class Weather(val query: WeatherQuery)
class WeatherQuery(val results: WeatherResult)
class WeatherResult(val channel: WeatherChannel)
class WeatherChannel(val title: String, val item: WeatherItem)
class WeatherItem(val forecast: List<Forecast>)
class Forecast(val date: String, val day: String,val high: String,val low: String,val text: String)



class WeatherRetriever{
    val service : WeatherAPI

    init{
        val retrofit = Retrofit.Builder().baseUrl("https://query.yahooapis.com/v1/public/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)

    }

    fun getForcast(callback: Callback<Weather>, searchTerm: String) {
        var searchT = searchTerm
        if (searchT==""){
            searchT = "New York City"
        }
        val q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"$searchT\")"
        val call = service.getForcast(q)
        call.enqueue(callback)
    }
}