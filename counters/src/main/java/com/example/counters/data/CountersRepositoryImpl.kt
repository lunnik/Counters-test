package com.example.counters.data

import com.example.counters.data.data_source.dummy.CountersDataSourceDummy
import com.example.counters.data.data_source.local.CountersDataSourceLocal
import com.example.counters.data.data_source.remote.CountersDataSourceRemote
import com.example.counters.domain.CountersRepository
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
import com.example.network.internet_connection.InternetConnectionRepository
import org.koin.standalone.KoinComponent

/** */
internal class CountersRepositoryImpl(
    private val countersDataSourceRemote: CountersDataSourceRemote,
    private val counterDataSourceLocal: CountersDataSourceLocal,
    private val countersDataSourceDummy: CountersDataSourceDummy,
    internetConnectionRepository: InternetConnectionRepository
) : CountersRepository,
    InternetConnectionRepository by internetConnectionRepository, KoinComponent {

    /** */
    private fun getCountersDataSource(): CountersDataSource =
        if (isOnline) countersDataSourceRemote else counterDataSourceLocal

    /** */
    override suspend fun getCounters():
            Either<GetCountersFailure, GetCountersResponse> =
        getCountersDataSource().getCounters()

    /** */
    override suspend fun increaseCounters(id: String):
            Either<IncreaseCounterFailure, IncreaseCounterResponse> =
        getCountersDataSource().increaseCounters(id)

    /** */
    override suspend fun decreaseCounters(id: String):
            Either<DecreaseCounterFailure, DecreaseCounterResponse> =
        getCountersDataSource().decreaseCounters(id)

    /** */
    override suspend fun deleteCounters(id: String):
            Either<DeleteCounterFailure, DeleteCounterResponse> =
        getCountersDataSource().deleteCounters(id)

    /** */
    override suspend fun addCounters(title: String):
            Either<AddCounterFailure, AddCounterResponse> =
        getCountersDataSource().addCounters(title)


}
