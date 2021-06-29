package com.example.counters.presentation.delete_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.counters.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.counters.domain.use_case.delete_counter.DeleteCounterParams
import com.example.counters.domain.use_case.delete_counter.DeleteCounterResponse
import com.example.counters.domain.use_case.delete_counter.DeleteCounterUseCase
import com.example.domain.Either
import com.example.domain.onLeft
import com.example.domain.onRight
import com.example.domain.presentation.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/**
 *
 */
internal class DeleteCounterImpl(
    private val deleteCounterUseCase: DeleteCounterUseCase
) : DeleteCounter {

    /* */
    override lateinit var deleteCountersResponse: DeleteCounterResponse

    /* */
    override lateinit var deleteCountersFailure: DeleteCounterFailure


    /** */
    override fun deleteCountersAsLiveData(
        params: DeleteCounterParams
    ): LiveData<DeleteCountersStatus> = flow<DeleteCountersStatus> {
        emit(Status.Loading())
        deleteCountersAsEither(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

    /** */
    override suspend fun deleteCountersAsEither(
        params: DeleteCounterParams
    ): Either<DeleteCounterFailure, DeleteCounterResponse> =
        deleteCounterUseCase.run(params)
            .onLeft { deleteCountersFailure = it }
            .onRight { deleteCountersResponse = it }

}