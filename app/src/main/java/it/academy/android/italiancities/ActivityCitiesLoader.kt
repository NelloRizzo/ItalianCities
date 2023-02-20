package it.academy.android.italiancities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import it.academy.android.italiancities.dao.MemoryCitiesDao
import it.academy.android.italiancities.dao.MemoryProvincesDao
import it.academy.android.italiancities.entities.City
import it.academy.android.italiancities.entities.Province

class ActivityCitiesLoader : AppCompatActivity() {
    companion object{
        const val TAG = "ActivityCitiesLoader"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // gestisce la pressione del pulsante di ritorno ad home
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_loader)

        // abilita il pulsante home sulla action bar
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.btn_load).setOnClickListener {
            // dobbiamo aprire il file indicato nella casella di testo
            openFileInput(findViewById<EditText>(R.id.filename).text.toString()).use {
                // dobbiamo creare un Dao per il salvataggio delle città
                val cities = MemoryCitiesDao()
                // dobbiamo creare un Dao per il salvataggio delle province
                val provinces = MemoryProvincesDao()
                // dobbiamo leggere tutte le righe del file
                it.reader().readLines()
                    // scarto le prime 3 di intestazione
                    .drop(3)
                    // le altre le gestisco
                    .forEach { line ->
                        // ogni riga devo spezzarla in maniera da poterne vedere i campi
                        val fields = line.split(";")
                        // per ogni riga creare
                        // una provincia da aggiungere al dao
                        val province = Province(fields[2].toLong(), fields[11], fields[14])
                        // e una città
                        val city = City(fields[4].toLong(), fields[5], fields[19], province)
                        // posso inserire città e province all'interno dei dao
                        provinces.save(province)
                        cities.save(city)
                    }

                Log.d(TAG, "Ho letto ${provinces.getAll().size} province")
            }
        }
    }
}