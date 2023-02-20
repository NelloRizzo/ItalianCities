package it.academy.android.italiancities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.academy.android.italiancities.adapters.CityAdapter
import it.academy.android.italiancities.adapters.ProvinceAdapter
import it.academy.android.italiancities.dao.DatabaseProvincesDao
import it.academy.android.italiancities.dao.MemoryCitiesDao
import it.academy.android.italiancities.dao.MemoryProvincesDao
import it.academy.android.italiancities.services.CitiesService
import it.academy.android.italiancities.services.CitiesServiceImpl

class CitiesSelectorActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CitiesSelectorActivity"
        const val PROVINCE_STATE = "acronym"
    }

    private var selectedProvince: String? = null

    private fun getService(): CitiesService {
        //val provinces: DatabaseProvincesDao = null
        return CitiesServiceImpl(MemoryProvincesDao(), MemoryCitiesDao())
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_selector)

        if (savedInstanceState?.containsKey(PROVINCE_STATE) == true) {
            selectedProvince = savedInstanceState.getString(PROVINCE_STATE)
            Log.d(TAG, "Ho recuperato il dato di selectedProvince: $selectedProvince")
        }
    }

    // salva le informazioni che voglio conservare tra onDestroy e onCreate
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState - selectedProvince vale $selectedProvince")
        outState.putString(PROVINCE_STATE, selectedProvince)
    }

    override fun onResume() {
        super.onResume()

        // ottengo un riferimento al servizio
        getService()
            // mi prendo tutte le province
            .getProvinces().run {
                // creo l'adapter
                val ad = ProvinceAdapter(this) {
                    showCities(it.acronym)
                }
                // per la RecyclerView mi serve anche un layout manager, perché essa non lo prevede
                // di default
                val lm = LinearLayoutManager(applicationContext).apply {
                    orientation = RecyclerView.VERTICAL
                }
                findViewById<RecyclerView>(R.id.list_provinces).apply {
                    adapter = ad
                    layoutManager = lm
                }
            }

        if (selectedProvince != null) showCities(selectedProvince!!)

        Log.d(TAG, "onResume()")
    }

    private fun showCities(acronym: String) {
        selectedProvince = acronym
        Log.d(TAG, "Hai cliccato su $acronym, selectedProvince vale $selectedProvince")
        // nascondo il testo di avviso che non ci sono province selezionate
        findViewById<TextView>(R.id.no_items_label).visibility = View.GONE
        // visualizzo la recyclerview
        findViewById<RecyclerView>(R.id.list_cities).visibility = View.VISIBLE
        // ottengo un riferimento al servizio
        getService()
            // ottengo le città della provincia selezionata
            .getCities(acronym).run {
                // preparo la recyclerview
                val ad = CityAdapter(this)
                // layout manager
                val lm = LinearLayoutManager(applicationContext).apply {
                    orientation = RecyclerView.VERTICAL
                }
                findViewById<RecyclerView>(R.id.list_cities).apply {
                    adapter = ad
                    layoutManager = lm
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        Log.d(TAG, "Ho creato il menu")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "Hai cliccato su ${item.title}")
        when (item.itemId) {
            R.id.load_cities -> startActivity(Intent(this, ActivityCitiesLoader::class.java))
            R.id.handle_database -> startActivity(Intent(this, DatabaseHandlerActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}