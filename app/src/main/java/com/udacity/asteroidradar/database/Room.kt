package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {


    @Query("SELECT * FROM databaseasteroid")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroid(vararg asteroid: DatabaseAsteroid)

}

@Database(entities = [DatabaseAsteroid::class], version = 1)
abstract class AsteroidDataBase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
}

private lateinit var INSTANCE: AsteroidDataBase

fun getDatabase(context: Context): AsteroidDataBase {
    synchronized(AsteroidDataBase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidDataBase::class.java,
                "astroids"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
