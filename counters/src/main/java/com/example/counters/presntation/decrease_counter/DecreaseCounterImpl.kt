package com.example.counters.presntation.decrease_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterFailure
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterParams
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterResponse
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterUseCase
import com.example.domain.Either
import com.example.domain.onLeft
import com.example.domain.onRight
import com.example.domain.presentation.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/**
 *
 */
internal class DecreaseCounterImpl(
    private val decreaseCounterUseCase: DecreaseCounterUseCase
) : DecreaseCounter {

    /* */
    override lateinit var decreaseCountersResponse: DecreaseCounterResponse

    /* */
    override lateinit var decreaseCountersFailure: DecreaseCounterFailure

    /** */
    override fun decreaseCountersAsLiveData(
        params: DecreaseCounterParams
    ): LiveData<DecreaseCountersStatus> = flow<DecreaseCountersStatus> {
        emit(Status.Loading())
        decreaseCountersAsEither(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

    /** */
    override suspend fun decreaseCountersAsEither(
        params: DecreaseCounterParams
    ): Either<DecreaseCounterFailure, DecreaseCounterResponse> =
        decreaseCounterUseCase.run(params)
            .onLeft { decreaseCountersFailure = it }
            .onRight { decreaseCountersResponse = it }

}