package it.academy.android.italiancities.dao

import it.academy.android.italiancities.entities.Province

/**
 * Il DAO ha responsabilità di accedere ai dati delle province.
 *
 * Esso mette a disposizione i metodi CRUD sulle province.
 */
interface ProvincesDao {
    /**
     * Salva una provincia sul database.
     */
    fun save(province: Province)

    /**
     * Recupera tutte le province dal database.
     * @return l'elenco di tutte le province.
     */
    fun getAll(): List<Province>
}