package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class country_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.country_detail)

        val text = findViewById<TextView>(R.id.country_detes)
        val sharedPref = this.getSharedPreferences("savefile", Context.MODE_PRIVATE)

        val country = sharedPref.getString("country", "").toString()
        val gold = sharedPref.getString("gold", "").toString()
        val silver = sharedPref.getString("silver", "").toString()
        val bronze = sharedPref.getString("bronze", "").toString()

        text.text = "The last Country pressed is: $country \nThe medals won by said country are: \nGold: $gold\nSilver: $silver\n Bronze: $bronze"


    }
}