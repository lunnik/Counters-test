package com.example.cache.data

import com.example.cache.domain.CounterRepository
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

internal class CounterDataSourceImpl(
    private val counterDataSource: CounterDataSource
) : CounterRepository {

    /** */
    override suspend fun addCounter(counters: List<Counter>):
            Either<AddCounterFailure, AddCounterResponse> =
        counterDataSource.addCounter(counters)

    /** */
    override suspend fun deleteCounter(counters: Counter):
            Either<DeleteCounterFailure, DeleteCounterResponse> =
        counterDataSource.deleteCounter(counters)

    /** */
    override suspend fun getCounters():
            Either<GetCountersFailure, GetCountersResponse> =
        counterDataSource.getCounters()


    /** */
    override suspend fun getCounterByTitle(title: String):
            Either<GetCounterByTitleFailure, GetCounterByTitleResponse> =
        counterDataSource.getCounterByTitle(title)

    /** */
    override suspend fun updateCounter(counters: Counter):
            Either<UpdateCounterFailure, UpdateCounterResponse> =
        counterDataSource.updateCounter(counters)
}