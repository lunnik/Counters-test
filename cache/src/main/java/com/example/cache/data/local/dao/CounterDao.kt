package com.example.cache.data.local.dao

import androidx.room.*
import com.example.cache.domain.entity.Counter

@Dao
interface CounterDao {

    /** */
    @Query("SELECT * FROM Counter")
    suspend fun getCounters(): List<Counter>

    /** */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCounters(counters: List<Counter>)

    @Delete
    suspend fun deleteCounter(counters:Counter)

    /** */
    @Update
    suspend fun updateCounter(counters:Counter)

    /** */
    @Query("SELECT * FROM Counter WHERE title=:title ")
    suspend fun getCountersByTitle(title:String): List<Counter>

}