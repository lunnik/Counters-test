package com.example.counters.domain.use_case.get_counters

import com.example.counters.domain.CountersRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class GetCountersUseCase(
    private val countersRepository: CountersRepository
) : UseCase<GetCountersResponse, GetCountersParams, GetCountersFailure>() {

    /** */
    override suspend fun run(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse> =
        countersRepository.getCounters()
}