package it.academy.android.italiancities.dao

import it.academy.android.italiancities.entities.Province

class MemoryProvincesDao : ProvincesDao {
    companion object {
        private val provinces: MutableSet<Province> = mutableSetOf()
    }

    override fun save(province: Province) {
        provinces.add(province)
    }

    override fun getAll(): List<Province> = provinces.toList()
}