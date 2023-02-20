package it.academy.android.italiancities.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey
    val id: Long,
    val name: String,
    val code: String,
) {
    @Ignore
    var province: Province? = null

    constructor(id: Long, name: String, code: String, province: Province) : this(id, name, code) {
        this.province = province
    }

}
