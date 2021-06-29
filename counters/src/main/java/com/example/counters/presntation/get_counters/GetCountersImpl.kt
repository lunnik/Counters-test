package com.example.counters.presntation.get_counters

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.domain.use_case.get_counters.GetCountersParams
import com.example.counters.domain.use_case.get_counters.GetCountersResponse
import com.example.counters.domain.use_case.get_counters.GetCountersUseCase
import com.example.domain.Either
import com.example.domain.onLeft
import com.example.domain.onRight
import com.example.domain.presentation.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/**
 *
 */
internal class GetCountersImpl(
    private val getCountersUseCase: GetCountersUseCase
) : GetCounters {

    /* */
    override lateinit var getCountersResponse: GetCountersResponse

    /* */
    override lateinit var getCountersFailure: GetCountersFailure


    /** */
    override fun getCountersAsLiveData(
        params: GetCountersParams
    ): LiveData<GetCountersStatus> = flow<GetCountersStatus> {
        emit(Status.Loading())
        getCountersAsEither(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

    /** */
    override suspend fun getCountersAsEither(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse> =
        getCountersUseCase.run(params)
            .onLeft { getCountersFailure = it }
            .onRight { getCountersResponse = it }

}