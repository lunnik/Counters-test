package com.example.counters.presntation.get_counters

import androidx.lifecycle.LiveData
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.domain.use_case.get_counters.GetCountersParams
import com.example.counters.domain.use_case.get_counters.GetCountersResponse
import com.example.domain.Either
import com.example.domain.presentation.Status

/* */
typealias GetCountersStatus = Status<GetCountersFailure, GetCountersResponse>

/** */
interface GetCounters {

    /* */
    val getCountersResponse: GetCountersResponse

    /* */
    val getCountersFailure: GetCountersFailure

    /** */
    fun getCountersAsLiveData(
        params: GetCountersParams
    ): LiveData<GetCountersStatus>

    /** */
    suspend fun getCountersAsEither(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse>

}
