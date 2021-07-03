package com.example.cache.domain.use_case.add_counter

import com.example.cache.domain.CounterRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class AddCounterUseCase(
    private val counterRepository: CounterRepository
) : UseCase<AddCounterResponse, AddCounterParams, AddCounterFailure>() {

    /** */
    override suspend fun run(
        params: AddCounterParams
    ): Either<AddCounterFailure, AddCounterResponse> =
        counterRepository.addCounter(
            params.counters
        )
}