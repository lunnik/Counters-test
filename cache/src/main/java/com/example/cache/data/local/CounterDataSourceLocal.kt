package com.example.cache.data.local

import android.util.Log
import com.example.data_source.data.exception.message
import com.example.cache.data.CounterDataSource
import com.example.cache.data.local.dao.CounterDao
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

@Suppress("UNCHECKED_CAST")
class CounterDataSourceLocal(
    private val counterDao: CounterDao
) : CounterDataSource {


    /** */
    override suspend fun addCounter(counters: List<Counter>): Either<AddCounterFailure, AddCounterResponse> =
        try {
            counterDao.addCounters(counters)
            Either.Right(AddCounterResponse)
        } catch (exception: Exception) {
            val failure = AddCounterFailure.UnknownFailure(exception.message())
            Either.Left(failure)
        }

    /** */
    override suspend fun deleteCounter(counters: Counter):
            Either<DeleteCounterFailure, DeleteCounterResponse> =
        try {
            counterDao.deleteCounter(counters)
            Either.Right(DeleteCounterResponse)
        } catch (exception: Exception) {
            val failure = DeleteCounterFailure.UnknownFailure(exception.message())
            Either.Left(failure)
        }

    override suspend fun getCounters():
            Either<GetCountersFailure, GetCountersResponse> =
        try {
            val countersResponse=counterDao.getCounters()
            Either.Right(GetCountersResponse(countersResponse))
        } catch (exception: Exception) {
            val failure = GetCountersFailure.UnknownFailure(exception.message())
            Either.Left(failure)
        }


    /** */
    override suspend fun getCounterByTitle(title: String):
            Either<GetCounterByTitleFailure, GetCounterByTitleResponse> =
        try {
            val counters=counterDao.getCountersByTitle(title)
            Either.Right(GetCounterByTitleResponse(counters))
        } catch (exception: Exception) {
            val failure = GetCounterByTitleFailure.UnknownFailure(exception.message())
            Either.Left(failure)
        }

    /** */
    override suspend fun updateCounter(counters: Counter):
            Either<UpdateCounterFailure, UpdateCounterResponse> =
        try {
            counterDao.updateCounter(counters)
            Either.Right(UpdateCounterResponse)
        } catch (exception: Exception) {
            val failure = UpdateCounterFailure.UnknownFailure(exception.message())
            Either.Left(failure)
        }
}