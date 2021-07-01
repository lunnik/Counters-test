package com.example.cache.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.domain.entity.Counter

@Dao
interface CounterDao {

    /** */
    @Query("SELECT * FROM Counter")
    suspend fun getAthleteActivity(): List<Counter>

    /** */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCounterActivity(athleteActivity: Counter): Long

}