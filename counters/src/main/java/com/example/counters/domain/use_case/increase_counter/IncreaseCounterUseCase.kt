package com.example.counters.domain.use_case.increase_counter

import com.example.counters.domain.CountersRepository
import com.example.domain.Either
import com.example.domain.UseCase

/**
 *
 */
class IncreaseCounterUseCase(
    private val countersRepository: CountersRepository
) : UseCase<IncreaseCounterResponse, IncreaseCounterParams, IncreaseCounterFailure>() {

    /** */
    override suspend fun run(
        params: IncreaseCounterParams
    ): Either<IncreaseCounterFailure, IncreaseCounterResponse> =
        countersRepository.increaseCounters(params.id)
}