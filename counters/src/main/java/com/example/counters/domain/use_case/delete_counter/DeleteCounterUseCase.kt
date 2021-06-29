package com.example.counters.domain.use_case.delete_counter

import com.example.counters.domain.CountersRepository
import com.example.domain.Either
import com.example.domain.UseCase

/** */
class DeleteCounterUseCase(
    private val countersRepository: CountersRepository
) : UseCase<DeleteCounterResponse, DeleteCounterParams, DeleteCounterFailure>() {

    /** */
    override suspend fun run(
        params: DeleteCounterParams
    ): Either<DeleteCounterFailure, DeleteCounterResponse> =
        countersRepository.deleteCounters()
}