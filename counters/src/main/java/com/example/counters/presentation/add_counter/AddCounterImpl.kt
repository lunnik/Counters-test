package com.example.counters.presentation.add_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.counters.domain.use_case.add_counter.AddCounterFailure
import com.example.counters.domain.use_case.add_counter.AddCounterParams
import com.example.counters.domain.use_case.add_counter.AddCounterResponse
import com.example.counters.domain.use_case.add_counter.AddCounterUseCase
import com.example.domain.Either
import com.example.domain.onLeft
import com.example.domain.onRight
import com.example.domain.presentation.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/**
 *
 */
internal class AddCounterImpl(
    private val addCounterUseCase: AddCounterUseCase
) : AddCounter {

    /* */
    override lateinit var addCounterResponse: AddCounterResponse

    /* */
    override lateinit var addCountersFailure: AddCounterFailure


    /** */
    override fun addCountersAsLiveData(
        params: AddCounterParams
    ): LiveData<AddCounterStatus> = flow<AddCounterStatus> {
        emit(Status.Loading())
        addCountersAsEither(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

    /** */
    override suspend fun addCountersAsEither(
        params: AddCounterParams
    ): Either<AddCounterFailure, AddCounterResponse> =
        addCounterUseCase.run(params)
            .onLeft { addCountersFailure = it }
            .onRight { addCounterResponse = it }

}