package it.academy.android.italiancities.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "provinces")
data class Province(
    @PrimaryKey
    val id: Long,
    val name: String,
    val acronym: String
)
