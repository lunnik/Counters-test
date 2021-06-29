package com.example.counters.domain.use_case.add_counter

import com.example.counters.domain.CountersRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class AddCounterUseCase(
    private val countersRepository: CountersRepository
) : UseCase<AddCounterResponse, AddCounterParams, AddCounterFailure>() {

    /** */
    override suspend fun run(
        params: AddCounterParams
    ): Either<AddCounterFailure, AddCounterResponse> =
        countersRepository.getCounters()
}