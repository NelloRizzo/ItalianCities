package it.academy.android.italiancities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import it.academy.android.italiancities.dao.CitiesDatabase
import it.academy.android.italiancities.dao.MemoryCitiesDao
import it.academy.android.italiancities.dao.MemoryProvincesDao
import it.academy.android.italiancities.services.CitiesServiceImpl
import java.util.concurrent.Executors

class DatabaseHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_handler)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        // impostiamo il gestore del click sul pulsante per il caricamento dei dati
        findViewById<Button>(R.id.load_data).setOnClickListener {
            saveData()
        }
    }

    fun saveData() {
        // le operazioni andranno fatte in un thread separato
        Executors.newSingleThreadExecutor().execute  {
            // costruisce in questo punto l'infrastruttura necessaria per operare con il DB
            val dao = Room
                // costruttore di un database
                .databaseBuilder(
                    // contesto di applicazione
                    applicationContext,
                    // la classe che implementa il database
                    CitiesDatabase::class.java,
                    // il nome del database
                    CitiesDatabase.DATABASE_NAME
                )
                // crea il db
                .build()
                // recupera il dao delle province
                .getProvincesDao()
            // prendiamo le province in memoria
            CitiesServiceImpl(MemoryProvincesDao(), MemoryCitiesDao()).getProvinces()
                // e per ognuna
                .forEach {
                    // le inseriamo sul database con l'istanza db
                    dao // recuperiamo il dao delle province
                        // e salviamo la provincia it con il metodo save!
                        .save(it)
                }
            findViewById<TextView>(R.id.provinces_count).text = dao.getAll().size.toString()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}