package com.example.counters.domain

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

/* */
interface CountersRepository {

    /** */
    suspend fun getCounters(): Either<GetCountersFailure, GetCountersResponse>

    /** */
    suspend fun increaseCounters(id:String): Either<IncreaseCounterFailure, IncreaseCounterResponse>

    /** */
    suspend fun decreaseCounters(id:String): Either<DecreaseCounterFailure, DecreaseCounterResponse>

    /** */
    suspend fun deleteCounters(id:String): Either<DeleteCounterFailure, DeleteCounterResponse>

    /** */
    suspend fun addCounters(title:String): Either<AddCounterFailure, AddCounterResponse>


}