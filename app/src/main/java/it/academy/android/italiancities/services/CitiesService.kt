package it.academy.android.italiancities.services

import it.academy.android.italiancities.entities.City
import it.academy.android.italiancities.entities.Province

/**
 * Le funzionalità indispensabili per la gestione dei dati.
 */
interface CitiesService {
    /**
     * Ottiene l'elenco di tutte le province (ordinate sul nome).
     * @return l'elenco di tutte le province italiane.
     */
    fun getProvinces(): List<Province>

    /**
     * Ottiene tutte le città che si trovano in una determinata provincia.
     * @param acronym la sigla di una provincia.
     * @return l'elenco di tutte le città che si trovano all'interno della provincia passata come parametro.
     */
    fun getCities(acronym: String): List<City>
}