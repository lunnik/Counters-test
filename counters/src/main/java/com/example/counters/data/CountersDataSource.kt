package com.example.counters.data

import com.example.counters.domain.use_case.add_counter.AddCounterFailure
import com.example.counters.domain.use_case.add_counter.AddCounterResponse
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterFailure
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterResponse
import com.example.counters.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.counters.domain.use_case.delete_counter.DeleteCounterResponse
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.domain.use_case.get_counters.GetCountersResponse
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterFailure
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterResponse
import com.example.domain.Either

/** */
internal interface CountersDataSource {

    /** */
    suspend fun getCounters(): Either<GetCountersFailure, GetCountersResponse>

    /** */
    suspend fun increaseCounters(): Either<IncreaseCounterFailure, IncreaseCounterResponse>

    /** */
    suspend fun decreaseCounters(): Either<DecreaseCounterFailure, DecreaseCounterResponse>

    /** */
    suspend fun deleteCounters(): Either<DeleteCounterFailure, DeleteCounterResponse>

    /** */
    suspend fun addCounters(title:String): Either<AddCounterFailure, AddCounterResponse>

}
