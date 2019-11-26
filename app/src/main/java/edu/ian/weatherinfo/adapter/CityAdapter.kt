package edu.ian.weatherinfo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import edu.ian.weatherinfo.CityWeather
import edu.ian.weatherinfo.R
import edu.ian.weatherinfo.WeatherList
import edu.ian.weatherinfo.WeatherList.Companion.CITY_NAME
import edu.ian.weatherinfo.touch.CityTouchHelperCallback
import kotlinx.android.synthetic.main.city_row.view.*
import java.util.*

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>, CityTouchHelperCallback {

    var cityList = mutableListOf<String>()

    val context: Context

    constructor(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cityRow = LayoutInflater.from(context).inflate(
            R.layout.city_row, parent, false
        )

        return ViewHolder(cityRow)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var city = cityList.get(holder.adapterPosition)

        holder.tvCityName.text = city
        holder.ivDelete.setImageResource(R.drawable.ic_clear)

        holder.ivDelete.setOnClickListener {
            deleteCity(holder.adapterPosition)
        }

        holder.tvCityName.setOnClickListener {
            val intent = Intent((context as WeatherList), CityWeather::class.java)
            intent.putExtra(CITY_NAME, holder.tvCityName.text.toString())
            context.startActivity(intent)
        }
    }


    fun updateCityOnPosition(city: String, index: Int) {
        cityList[index] = city
        notifyItemChanged(index)
    }

    private fun deleteCity(index: Int) {
        cityList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addCity(city: String) {
        cityList.add(city)
        notifyItemInserted(cityList.lastIndex)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCityName = itemView.tvCityName
        val ivDelete = itemView.ivDelete
    }

    override fun onDismissed(position: Int) {
        deleteCity(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(cityList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}