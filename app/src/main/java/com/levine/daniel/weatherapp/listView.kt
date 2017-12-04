package com.levine.daniel.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class listView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val searchTerm = intent.extras.getString("searchTerm")

        var retriever = WeatherRetriever()

        val callback = object : Callback<Weather>{
            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("it failed")
            }

            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("we got a response")
                title = response?.body()?.query?.results?.channel?.title


                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast

                var forecastStrings = mutableListOf<String>()

                println("Hererere"+response?.body()?.query?.results?.channel?.title)

                if (forecasts!=null){
                    for (forecast in forecasts) {
                        val newString = "${forecast.date}- High:${forecast.high} Low:${forecast.low} - ${forecast.text}"
                        forecastStrings.add(newString)


                    }
                }

                var listView = findViewById<ListView>(R.id.forcastListView)


                var adapter = ArrayAdapter(this@listView, android.R.layout.simple_list_item_1, forecastStrings)

                listView.adapter = adapter
            }

        }
        retriever.getForcast(callback,searchTerm)
    }
}
