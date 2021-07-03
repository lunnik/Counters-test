package com.example.cache.domain

import com.example.cache.domain.entity.Counter
import com.example.cache.domain.use_case.add_counter.AddCounterFailure
import com.example.cache.domain.use_case.add_counter.AddCounterResponse
import com.example.cache.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.cache.domain.use_case.delete_counter.DeleteCounterResponse
import com.example.cache.domain.use_case.get_counters.GetCountersFailure
import com.example.cache.domain.use_case.get_counters.GetCountersResponse
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleFailure
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleResponse
import com.example.cache.domain.use_case.update_counter.UpdateCounterFailure
import com.example.cache.domain.use_case.update_counter.UpdateCounterResponse
import com.example.domain.Either

interface CounterRepository {

    /** */
    suspend fun addCounter(counters: List<Counter>):
            Either<AddCounterFailure, AddCounterResponse>


    /** */
    suspend fun deleteCounter(counters: Counter):
            Either<DeleteCounterFailure, DeleteCounterResponse>

    /** */
    suspend fun getCounters():
            Either<GetCountersFailure, GetCountersResponse>


    /** */
    suspend fun getCounterByTitle(title: String):
            Either<GetCounterByTitleFailure, GetCounterByTitleResponse>


    /** */
    suspend fun updateCounter(counters: Counter):
            Either<UpdateCounterFailure, UpdateCounterResponse>


}