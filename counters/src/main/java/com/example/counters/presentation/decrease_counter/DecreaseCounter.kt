package com.example.counters.presentation.decrease_counter

import androidx.lifecycle.LiveData
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterFailure
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterParams
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterResponse
import com.example.domain.Either
import com.example.domain.presentation.Status

/* */
typealias DecreaseCountersStatus = Status<DecreaseCounterFailure, DecreaseCounterResponse>

/** */
interface DecreaseCounter {

    /* */
    val decreaseCountersResponse: DecreaseCounterResponse

    /* */
    val decreaseCountersFailure: DecreaseCounterFailure

    /** */
    fun decreaseCountersAsLiveData(
        params: DecreaseCounterParams
    ): LiveData<DecreaseCountersStatus>

    /** */
    suspend fun decreaseCountersAsEither(
        params: DecreaseCounterParams
    ): Either<DecreaseCounterFailure, DecreaseCounterResponse>

}
