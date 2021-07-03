package com.example.counters.data.data_source.remote

import android.util.Log
import com.example.cache.data.local.dao.CounterDao
import com.example.data_source.data.exception.message
import com.example.counters.data.CountersDataSource
import com.example.counters.data.data_source.remote.model.AddCounterRequest
import com.example.counters.data.data_source.remote.model.DecreaseCounterRequest
import com.example.counters.data.data_source.remote.model.DeleteCounterRequest
import com.example.counters.data.data_source.remote.model.IncreaseCounterRequest
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
import com.example.data_source.data.remote.model.retrofitApiCall
import com.example.domain.Either
import retrofit2.HttpException

/** */
internal class CountersDataSourceRemote(
    private val countersApiService: CountersApiService,
    private val counterDao: CounterDao,
) : CountersDataSource {

    /** */
    override suspend fun getCounters():
            Either<GetCountersFailure, GetCountersResponse> = try {
        retrofitApiCall {
            countersApiService.getCounters()
        }.let { httpResponse ->
            val counters = httpResponse.map { it.toCounter() }
            counterDao.addCounters(counters)
            Either.Right(GetCountersResponse(counters))
        }
    } catch (exception: Exception) {
        val failure: GetCountersFailure = when (exception) {
            is HttpException -> exception.toGetCountersFailure()
            else -> GetCountersFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

    /** */
    override suspend fun increaseCounters(id: String): Either<IncreaseCounterFailure, IncreaseCounterResponse> =
        try {
            retrofitApiCall {
                val request = IncreaseCounterRequest(id)
                countersApiService.increaseCounter(request)
            }.let { httpResponse ->
                val counters = httpResponse.map { it.toCounter() }
                Either.Right(IncreaseCounterResponse(counters))
            }
        } catch (exception: Exception) {
            val failure: IncreaseCounterFailure = when (exception) {
                is HttpException -> exception.toIncreaseCounterFailure()
                else -> IncreaseCounterFailure.UnknownFailure(exception.message())
            }
            Either.Left(failure)
        }

    /** */
    override suspend fun decreaseCounters(id: String): Either<DecreaseCounterFailure, DecreaseCounterResponse> =
        try {
            retrofitApiCall {
                val request = DecreaseCounterRequest(id)
                countersApiService.decreaseCounter(request)
            }.let { httpResponse ->
                val counters = httpResponse.map { it.toCounter() }
                Either.Right(DecreaseCounterResponse(counters))
            }
        } catch (exception: Exception) {
            val failure: DecreaseCounterFailure = when (exception) {
                is HttpException -> exception.toDecreaseCounterFailure()
                else -> DecreaseCounterFailure.UnknownFailure(exception.message())
            }
            Either.Left(failure)
        }

    /** */
    override suspend fun deleteCounters(id: String): Either<DeleteCounterFailure, DeleteCounterResponse> =
        try {
            retrofitApiCall {
                val request = DeleteCounterRequest(id)
                countersApiService.deleteCounter(request)
            }.let { httpResponse ->
                val counters = httpResponse.map { it.toCounter() }
                Either.Right(DeleteCounterResponse(counters))
            }
        } catch (exception: Exception) {
            Log.e("exception",exception.toString())
            val failure: DeleteCounterFailure = when (exception) {
                is HttpException -> exception.toDeleteCounterFailure()
                else -> DeleteCounterFailure.UnknownFailure(exception.message())
            }
            Either.Left(failure)
        }


    /** */
    override suspend fun addCounters(title: String): Either<AddCounterFailure, AddCounterResponse> =
        try {
            retrofitApiCall {
                val addCounterRequest = AddCounterRequest(title)
                countersApiService.addCounter(addCounterRequest)
            }.let { httpResponse ->
                Either.Right(AddCounterResponse(httpResponse.map { it.toCounter() }))
            }
        } catch (exception: Exception) {
            val failure: AddCounterFailure = when (exception) {
                is HttpException -> exception.toAddCounterFailure()
                else -> AddCounterFailure.UnknownFailure(exception.message())
            }
            Either.Left(failure)
        }

}
