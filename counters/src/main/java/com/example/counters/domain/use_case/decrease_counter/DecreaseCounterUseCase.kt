package com.example.counters.domain.use_case.decrease_counter

import com.example.counters.domain.CountersRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class DecreaseCounterUseCase(
    private val countersRepository: CountersRepository
) : UseCase<DecreaseCounterResponse, DecreaseCounterParams, DecreaseCounterFailure>() {

    /**c*/
    override suspend fun run(
        params: DecreaseCounterParams
    ): Either<DecreaseCounterFailure, DecreaseCounterResponse> =
        countersRepository.decreaseCounters(params.id)
}