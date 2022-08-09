package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Dao
interface AsteroidDao {
//put your Query
}

@Database(entities = [AsteroidEntities::class], version = 1, exportSchema = false)
abstract class AsteroidDataBase : RoomDatabase() {

    abstract val asteroidDao: AsteroidDao

    companion object {
        @Volatile
        private var INSTANCE: AsteroidDataBase? = null

        fun getInstance(context: Context): AsteroidDataBase? {
            var instance = INSTANCE
            synchronized(this) {
                if (INSTANCE == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDataBase::class.java,
                        AsteroidEntities.TABLE_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
            }
            return instance
        }
    }
}