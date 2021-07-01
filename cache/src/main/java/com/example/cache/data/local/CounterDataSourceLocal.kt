package com.example.cache.data.local

import com.example.data_source.data.exception.message
import com.example.cache.data.CounterDataSource
import com.example.cache.data.local.dao.CounterDao
import com.example.cache.domain.entity.Counter
import com.example.cache.domain.use_case.add_counter.AddCounterFailure
import com.example.cache.domain.use_case.add_counter.AddCounterResponse
import com.example.domain.Either

@Suppress("UNCHECKED_CAST")
class CounterDataSourceLocal(
    private val counterDao: CounterDao
) : CounterDataSource {


    /** */
    override suspend fun addCounterActivity(counters: List<Counter>): Either<AddCounterFailure, AddCounterResponse> =
        try {
            counterDao.addCounters(counters)
            Either.Right(AddCounterResponse)
        } catch (exception: Exception) {
            val failure = AddCounterFailure.UnknownFailure(exception.message())
            Either.Left(failure)
        }
}