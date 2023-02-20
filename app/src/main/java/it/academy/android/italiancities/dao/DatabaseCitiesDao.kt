package it.academy.android.italiancities.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.academy.android.italiancities.entities.City

/**
 * Dao per la gestione delle città su Database.
 * Poiché mi serviranno gli stessi metodi che ho usato in precedenza
 * lo devo comunicare implementando una "ereditarietà tra interfacce",
 * così questa interfaccia è compatibile con CitiesDao che ho usato nel mio "servizio
 * di gestione delle città".
 */
// questa annotazione crea il necessario per gestire i dati sul database
@Dao
interface DatabaseCitiesDao : CitiesDao {
    /**
     * Recupero tutte le città dal database.
     * @return l'elenco delle città sul database.
     */
    // per il recupero dei dati ricorriamo all'attributo Query!
    @Query("SELECT * FROM cities WHERE code = :acronym")
    override fun getAll(acronym: String): List<City>

    /**
     * Salvo una città sul database.
     * @param city la città da salvare sul database.
     */
    // questo è il metodo per l'inserimento dei dati sul database
    @Insert
    override fun save(city: City)
}