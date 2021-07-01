package com.example.cache.data

import com.example.cache.domain.entity.Counter
import com.example.cache.domain.use_case.add_counter.AddCounterFailure
import com.example.cache.domain.use_case.add_counter.AddCounterResponse
import com.example.domain.Either

internal interface CounterDataSource {

    /** */
    suspend fun addCounterActivity(counters: List<Counter>):
            Either<AddCounterFailure, AddCounterResponse>
}