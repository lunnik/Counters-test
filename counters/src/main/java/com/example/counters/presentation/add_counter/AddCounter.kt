package com.example.counters.presentation.add_counter

import androidx.lifecycle.LiveData
import com.example.counters.domain.use_case.add_counter.AddCounterFailure
import com.example.counters.domain.use_case.add_counter.AddCounterParams
import com.example.counters.domain.use_case.add_counter.AddCounterResponse
import com.example.domain.Either
import com.example.domain.presentation.Status

/* */
typealias AddCounterStatus = Status<AddCounterFailure, AddCounterResponse>

/** */
interface AddCounter {

    /* */
    val addCounterResponse: AddCounterResponse

    /* */
    val addCountersFailure: AddCounterFailure

    /** */
    fun addCountersAsLiveData(
        params: AddCounterParams
    ): LiveData<AddCounterStatus>

    /** */
    suspend fun addCountersAsEither(
        params: AddCounterParams
    ): Either<AddCounterFailure, AddCounterResponse>

}
