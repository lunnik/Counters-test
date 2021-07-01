package com.example.cache.domain

import com.example.cache.domain.entity.Counter
import com.example.cache.domain.use_case.add_counter.AddCounterFailure
import com.example.cache.domain.use_case.add_counter.AddCounterResponse
import com.example.domain.Either

interface CounterRepository {

    /** */
    suspend fun addCounterActivity(counter: Counter):
            Either<AddCounterFailure, AddCounterResponse>

}