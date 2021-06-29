package com.example.counters.presentation.increase_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterFailure
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterParams
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterResponse
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterUseCase
import com.example.domain.Either
import com.example.domain.onLeft
import com.example.domain.onRight
import com.example.domain.presentation.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/**
 *
 */
internal class IncreaseCounterImpl(
    private val increaseCounterUseCase: IncreaseCounterUseCase
) : IncreaseCounter {

    /* */
    override lateinit var increaseCountersResponse: IncreaseCounterResponse

    /* */
    override lateinit var increaseCountersFailure: IncreaseCounterFailure

    /** */
    override fun increaseCountersAsLiveData(
        params: IncreaseCounterParams
    ): LiveData<IncreaseCountersStatus> = flow<IncreaseCountersStatus> {
        emit(Status.Loading())
        increaseCountersAsEither(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

    /** */
    override suspend fun increaseCountersAsEither(
        params: IncreaseCounterParams
    ): Either<IncreaseCounterFailure, IncreaseCounterResponse> =
        increaseCounterUseCase.run(params)
            .onLeft { increaseCountersFailure = it }
            .onRight { increaseCountersResponse = it }

}