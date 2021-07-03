package com.example.cache.domain.use_case.delete_counter

import com.example.cache.domain.CounterRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class DeleteCounterUseCase(
    private val counterRepository: CounterRepository
) : UseCase<DeleteCounterResponse, DeleteCounterParams, DeleteCounterFailure>() {

    /** */
    override suspend fun run(
        params: DeleteCounterParams
    ): Either<DeleteCounterFailure, DeleteCounterResponse> =
        counterRepository.deleteCounter(
            params.counter
        )
}