package com.example.counters.data

import com.example.counters.domain.CountersRepository
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
    private val countersDataSource: CountersDataSource,
    internetConnectionRepository: InternetConnectionRepository
) : CountersRepository,
    InternetConnectionRepository by internetConnectionRepository, KoinComponent {

    /** */
    override suspend fun getCounters(): Either<GetCountersFailure, GetCountersResponse> =
        if (isOnline)
            countersDataSource.getCounters()
        else Either.Left(GetCountersFailure.NetworkConnectionFailure)

    /** */
    override suspend fun increaseCounters(): Either<IncreaseCounterFailure, IncreaseCounterResponse> =
        if (isOnline)
            countersDataSource.increaseCounters()
        else Either.Left(IncreaseCounterFailure.NetworkConnectionFailure)

    /** */
    override suspend fun decreaseCounters(): Either<DecreaseCounterFailure, DecreaseCounterResponse> =
        if (isOnline)
            countersDataSource.decreaseCounters()
        else Either.Left(DecreaseCounterFailure.NetworkConnectionFailure)

    /** */
    override suspend fun deleteCounters(): Either<DeleteCounterFailure, DeleteCounterResponse> =
        if (isOnline)
            countersDataSource.deleteCounters()
        else Either.Left(DeleteCounterFailure.NetworkConnectionFailure)


}
