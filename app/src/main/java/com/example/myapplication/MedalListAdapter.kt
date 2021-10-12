package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MedalListAdapter(private val data: List<medalist>, private val OnCountryCLickListener: OnCountryCLickListener): RecyclerView.Adapter<MedalListAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.medal_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            OnCountryCLickListener.onCountryItemClicked(item)
        }
    }

    class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        private val country: TextView = v.findViewById(R.id.country)
        private val ioc: TextView = v.findViewById(R.id.ioc)
        private val medals: TextView = v.findViewById(R.id.medals)

        fun bind(item: medalist){
            val allmedals = item.gold.toInt()+item.silver.toInt()+item.bronze.toInt()
            country.text = item.country
            ioc.text = item.IOC
            medals.text = allmedals.toString()
            when {
                item.gold.toInt() > item.silver.toInt() && item.gold.toInt() > item.bronze.toInt() && allmedals != 0 ->{
                    medals.setTextColor(Color.parseColor("#ffeb3b"))
                }
                item.silver.toInt()>item.gold.toInt() && item.silver.toInt() > item.bronze.toInt() && allmedals != 0-> {
                    medals.setTextColor(Color.parseColor("#a4a4a4"))
                }
                item.bronze.toInt()>item.gold.toInt() && item.bronze.toInt() > item.silver.toInt() && allmedals != 0-> {
                    medals.setTextColor(Color.parseColor("#c7b800"))
                }
            }
            }
        }
    }