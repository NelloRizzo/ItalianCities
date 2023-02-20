package it.academy.android.italiancities.dao

import it.academy.android.italiancities.entities.City

class MemoryCitiesDao : CitiesDao {
    companion object {
        private val cities = mutableSetOf<City>()
    }

    override fun save(city: City) {
        cities.add(city)
    }

    override fun getAll(acronym: String): List<City> =
        cities.filter { c -> c.province?.acronym == acronym }.toList()
}