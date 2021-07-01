package com.example.cache.domain.use_case.add_counter

import com.example.cache.domain.CounterRepository
import com.example.cache.domain.entity.Counter
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
        counterRepository.addCounterActivity(
            Counter(
                count = params.count,
                id = params.id,
                title = params.title)
        )
}