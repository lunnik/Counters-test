package com.example.counters.presentation.increase_counter

import androidx.lifecycle.LiveData
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterFailure
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterParams
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterResponse
import com.example.domain.Either
import com.example.domain.presentation.Status

/* */
typealias IncreaseCountersStatus = Status<IncreaseCounterFailure, IncreaseCounterResponse>

/** */
interface IncreaseCounter {

    /* */
    val increaseCountersResponse: IncreaseCounterResponse

    /* */
    val increaseCountersFailure: IncreaseCounterFailure

    /** */
    fun increaseCountersAsLiveData(
        params: IncreaseCounterParams
    ): LiveData<IncreaseCountersStatus>

    /** */
    suspend fun increaseCountersAsEither(
        params: IncreaseCounterParams
    ): Either<IncreaseCounterFailure, IncreaseCounterResponse>

}
