package edu.ian.weatherinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.ian.weatherinfo.WeatherList.Companion.CITY_NAME
import kotlinx.android.synthetic.main.activity_city_weather.*

class CityWeather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather)
        supportActionBar?.setIcon(R.drawable.ic_weather)

        val cityName = intent.getStringExtra(CITY_NAME)
        tvCity.text = cityName
    }
}
