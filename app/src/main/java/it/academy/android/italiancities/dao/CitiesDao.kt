package it.academy.android.italiancities.dao

import it.academy.android.italiancities.entities.City

/**
 * Il DAO ha responsabilità di accedere ai dati delle città.
 *
 * Esso mette a disposizione i metodi CRUD sulle città.
 */
interface CitiesDao {
    /**
     * Salvare una città sul database.
     */
    fun save(city: City)

    /**
     * Recuperare le città appartenenti ad una provincia.
     * @param acronym la sigla della provincia.
     * @return le città appartenenti alla provincia.
     */
    fun getAll(acronym: String): List<City>
}