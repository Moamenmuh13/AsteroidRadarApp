package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.database.AsteroidEntities.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class AsteroidEntities(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) {
    companion object {
        const val TABLE_NAME = "asteroids"

    }
}