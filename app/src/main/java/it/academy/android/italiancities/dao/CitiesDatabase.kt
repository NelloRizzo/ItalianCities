package it.academy.android.italiancities.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import it.academy.android.italiancities.entities.City
import it.academy.android.italiancities.entities.Province

/**
 * Il gestore della persistenza su database.
 */
@Database(
    // versione attuale
    version = CitiesDatabase.VERSION,
    // entità gestite
    entities = [City::class, Province::class]
)
abstract class CitiesDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "cities"
        const val VERSION = 1
    }

    abstract fun getCitiesDao(): DatabaseCitiesDao
    abstract fun getProvincesDao(): DatabaseProvincesDao
}