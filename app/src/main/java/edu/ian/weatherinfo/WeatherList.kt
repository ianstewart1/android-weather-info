package edu.ian.weatherinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import edu.ian.weatherinfo.adapter.CityAdapter
import kotlinx.android.synthetic.main.activity_scrolling.*

class WeatherList : AppCompatActivity(), NewCityDialog.CityHandler {

    companion object {
        val TAG_CITY = "TAG_CITY"
        val CITY_NAME = "CITY_NAME"
    }

    lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.ic_weather)

        initRecyclerView()

        btnAdd.setOnClickListener { view ->
            showAddCityDialog()
        }
    }

    private fun initRecyclerView() {
        cityAdapter = CityAdapter(this)
        recyclerCity.adapter = cityAdapter

        var itemDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )

        recyclerCity.addItemDecoration(itemDecoration)
    }

    private fun showAddCityDialog() {
        NewCityDialog().show(supportFragmentManager, TAG_CITY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // toolbar menu stuff
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // toolbar menu stuff
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveCity(city: String) {
        cityAdapter.addCity(city)
    }

    override fun cityAdded(city: String) {
        saveCity(city)
    }
}
