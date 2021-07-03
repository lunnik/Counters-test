package com.example.cache.presentation.get_counters_by_title

import androidx.lifecycle.LiveData
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleFailure
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleParams
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleResponse
import com.example.domain.Either
import com.example.domain.presentation.Status

/* */
typealias GetCountersByTitleStatus = Status<GetCounterByTitleFailure, GetCounterByTitleResponse>

/** */
interface GetCounterByTitle {

    /* */
    val getCounterByTitleResponse: GetCounterByTitleResponse

    /* */
    val getCounterByTitleFailure: GetCounterByTitleFailure

    /** */
    fun getCountersByTitleAsLiveData(
        params: GetCounterByTitleParams
    ): LiveData<GetCountersByTitleStatus>

    /** */
    suspend fun getCountersByTitleAsEither(
        params: GetCounterByTitleParams
    ): Either<GetCounterByTitleFailure, GetCounterByTitleResponse>

}
