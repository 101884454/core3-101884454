package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() ,OnCountryCLickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val medalist = mutableListOf<medalist>()
        resources.openRawResource(R.raw.medallists).bufferedReader()
            .forEachLine {
                val temp = it.split(",")
                medalist.add(medalist(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]))
            }

        val list = findViewById<RecyclerView>(R.id.listofmedals)
        list.adapter = MedalListAdapter(medalist, this)
        list.layoutManager = LinearLayoutManager(this)

    }

    override fun onCountryItemClicked(data: medalist) {
        Toast.makeText(this, ""+data.country+" has won Gold: "+data.gold+" Silver: "+data.silver+" Bronze: "+data.bronze+" medals",
            Toast.LENGTH_LONG).show()
        val sharedPref = this.getSharedPreferences("savefile", Context.MODE_PRIVATE)?: return
        with (sharedPref.edit()){
            putString("country", data.country)
            putString("gold", data.gold)
            putString("silver", data.silver)
            putString("bronze", data.bronze)
            apply()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, country_details::class.java)
        return when (item.itemId) {
            R.id.nav_save -> {
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

