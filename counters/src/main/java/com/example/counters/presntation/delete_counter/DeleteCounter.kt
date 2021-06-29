package com.example.counters.presntation.delete_counter

import androidx.lifecycle.LiveData
import com.example.counters.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.counters.domain.use_case.delete_counter.DeleteCounterParams
import com.example.counters.domain.use_case.delete_counter.DeleteCounterResponse
import com.example.domain.Either
import com.example.domain.presentation.Status

/* */
typealias DeleteCountersStatus = Status<DeleteCounterFailure, DeleteCounterResponse>

/** */
interface DeleteCounter {

    /* */
    val deleteCountersResponse: DeleteCounterResponse

    /* */
    val deleteCountersFailure: DeleteCounterFailure

    /** */
    fun deleteCountersAsLiveData(
        params: DeleteCounterParams
    ): LiveData<DeleteCountersStatus>

    /** */
    suspend fun deleteCountersAsEither(
        params: DeleteCounterParams
    ): Either<DeleteCounterFailure, DeleteCounterResponse>

}
