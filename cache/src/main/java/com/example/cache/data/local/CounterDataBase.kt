package com.example.cache.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.data.local.dao.CounterDao
import com.example.cache.domain.entity.Counter

@Database(
    entities = [Counter::class],
    version = 1
)
abstract class CounterDataBase : RoomDatabase() {

    /** */
    abstract fun athleteActivityDao(): CounterDao

    /** */
    companion object {

        /** */
        fun getInstance(context: Context): CounterDataBase {
            synchronized(CounterDataBase::class.java) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    CounterDataBase::class.java, DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
            }

        }

        /* */
        private const val DB_NAME = "athlete_activity.db"
    }

}