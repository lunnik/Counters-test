package com.example.cache.domain.use_case.get_counters_by_titile

import com.example.cache.domain.CounterRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class GetCounterByTitleUseCase(
    private val counterRepository: CounterRepository
) : UseCase<GetCounterByTitleResponse, GetCounterByTitleParams, GetCounterByTitleFailure>() {

    /** */
    override suspend fun run(
        params: GetCounterByTitleParams
    ): Either<GetCounterByTitleFailure, GetCounterByTitleResponse> =
        counterRepository.getCounterByTitle(params.title)
}