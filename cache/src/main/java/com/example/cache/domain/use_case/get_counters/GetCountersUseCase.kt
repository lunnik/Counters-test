package com.example.cache.domain.use_case.get_counters

import com.example.cache.domain.CounterRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class GetCountersUseCase(
    private val counterRepository: CounterRepository
) : UseCase<GetCountersResponse, GetCountersParams, GetCountersFailure>() {

    /** */
    override suspend fun run(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse> =
        counterRepository.getCounters()
}