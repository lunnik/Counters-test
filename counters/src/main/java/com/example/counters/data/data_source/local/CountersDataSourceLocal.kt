package com.example.counters.data.data_source.local

import com.example.cache.data.local.dao.CounterDao
import com.example.counters.data.CountersDataSource
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


internal class CountersDataSourceLocal(
    private val counterDao: CounterDao
) : CountersDataSource {
    /** */
    override suspend fun getCounters(): Either<GetCountersFailure, GetCountersResponse> =
        Either.Right(GetCountersResponse(counterDao.getCounters()))

    /** */
    override suspend fun increaseCounters(id: String): Either<IncreaseCounterFailure, IncreaseCounterResponse> =
        Either.Left(IncreaseCounterFailure.NetworkConnectionFailure)

    /** */
    override suspend fun decreaseCounters(id: String): Either<DecreaseCounterFailure, DecreaseCounterResponse> =
        Either.Left(DecreaseCounterFailure.NetworkConnectionFailure)

    /** */
    override suspend fun deleteCounters(id: String): Either<DeleteCounterFailure, DeleteCounterResponse> =
        Either.Left(DeleteCounterFailure.NetworkConnectionFailure)

    /** */
    override suspend fun addCounters(title: String): Either<AddCounterFailure, AddCounterResponse> =
        Either.Left(AddCounterFailure.NetworkConnectionFailure)

}