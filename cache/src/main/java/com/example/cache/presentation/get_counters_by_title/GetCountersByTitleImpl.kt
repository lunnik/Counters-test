package com.example.cache.presentation.get_counters_by_title

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleFailure
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleParams
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleResponse
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleUseCase
import com.example.domain.Either
import com.example.domain.onLeft
import com.example.domain.onRight
import com.example.domain.presentation.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/**
 *
 */
internal class GetCountersByTitleImpl(
    private val getCounterByTitleUseCase: GetCounterByTitleUseCase
) : GetCounterByTitle {


    /* */
    override lateinit var getCounterByTitleResponse: GetCounterByTitleResponse

    /* */
    override lateinit var getCounterByTitleFailure: GetCounterByTitleFailure

    /** */
    override fun getCountersByTitleAsLiveData(
        params: GetCounterByTitleParams
    ):
            LiveData<GetCountersByTitleStatus> = flow<GetCountersByTitleStatus> {
        emit(Status.Loading())
        getCountersByTitleAsEither(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

    /** */
    override suspend fun getCountersByTitleAsEither(
        params: GetCounterByTitleParams
    ): Either<GetCounterByTitleFailure, GetCounterByTitleResponse> =
        getCounterByTitleUseCase.run(params)
            .onLeft { getCounterByTitleFailure = it }
            .onRight { getCounterByTitleResponse = it }

}