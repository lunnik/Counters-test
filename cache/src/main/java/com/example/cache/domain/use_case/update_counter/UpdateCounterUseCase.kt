package com.example.cache.domain.use_case.update_counter

import com.example.cache.domain.CounterRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class UpdateCounterUseCase(
    private val counterRepository: CounterRepository
) : UseCase<UpdateCounterResponse, UpdateParams, UpdateCounterFailure>() {

    /** */
    override suspend fun run(
        params: UpdateParams
    ): Either<UpdateCounterFailure, UpdateCounterResponse> =
        counterRepository.updateCounter(
            params.counters
        )
}