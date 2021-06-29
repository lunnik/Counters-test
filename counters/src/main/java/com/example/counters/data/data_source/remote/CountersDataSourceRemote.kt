package com.example.counters.data.data_source.remote

import android.util.Log
import com.example.data_source.data.exception.message
import com.example.counters.data.CountersDataSource
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterFailure
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterResponse
import com.example.counters.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.counters.domain.use_case.delete_counter.DeleteCounterResponse
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.domain.use_case.get_counters.GetCountersResponse
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterFailure
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterResponse
import com.example.data_source.data.remote.model.retrofitApiCall
import com.example.domain.Either
import retrofit2.HttpException

/** */
internal class CountersDataSourceRemote(
    private val countersApiService: CountersApiService
) : CountersDataSource {

    /** */
    override suspend fun getCounters():
            Either<GetCountersFailure, GetCountersResponse> = try {
        retrofitApiCall {
            countersApiService.getCounters()
        }.let { httpResponse ->
            Either.Right(GetCountersResponse(httpResponse.map { it.toCounter() }))
        }
    } catch (exception: Exception) {
        val failure: GetCountersFailure = when (exception) {
            is HttpException -> exception.toGetCountersFailure()
            else -> GetCountersFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

    override suspend fun increaseCounters(): Either<IncreaseCounterFailure, IncreaseCounterResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun decreaseCounters(): Either<DecreaseCounterFailure, DecreaseCounterResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCounters(): Either<DeleteCounterFailure, DeleteCounterResponse> {
        TODO("Not yet implemented")
    }


}
