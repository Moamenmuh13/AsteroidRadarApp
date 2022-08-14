package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {


    @Query("SELECT * FROM databaseasteroid ORDER BY closeApproachDate DESC")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroid(vararg asteroid: DatabaseAsteroid)

}

@Database(entities = [DatabaseAsteroid::class], version = 1, exportSchema = false)
abstract class AsteroidDataBase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao

    companion object {
        @Volatile
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
    }


}
