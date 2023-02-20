package it.academy.android.italiancities.services

import it.academy.android.italiancities.dao.CitiesDao
import it.academy.android.italiancities.dao.ProvincesDao
import it.academy.android.italiancities.entities.City
import it.academy.android.italiancities.entities.Province

/**
 * Implementazione di default del servizio di gestione delle città.
 *
 *   Il servizio deve poter accedere allo storage responsabile
 *   della persistenza dei dati e renderli disponibili per le
 *   funzionalità offerte.
 */
class CitiesServiceImpl(private val provincesDao: ProvincesDao, private val citiesDao: CitiesDao) :
    CitiesService {
    override fun getProvinces(): List<Province> = provincesDao.getAll().sortedBy { p -> p.name }

    override fun getCities(acronym: String): List<City> =
        citiesDao.getAll(acronym).sortedBy { c -> c.name }
}