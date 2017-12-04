package com.levine.daniel.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var getForcast = findViewById<Button>(R.id.getForcastButton)
        getForcast.setOnClickListener {
            var moveToForcast = Intent(getApplicationContext(), listView::class.java)
            val searchEditText = findViewById<EditText>(R.id.editText2)
            moveToForcast.putExtra("searchTerm", searchEditText.text.toString())
            startActivity(moveToForcast)
        }
    }



}