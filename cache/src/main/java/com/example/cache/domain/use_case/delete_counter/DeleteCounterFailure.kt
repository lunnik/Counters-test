package com.example.cache.domain.use_case.delete_counter

import com.example.domain.Failure

/** */
sealed class DeleteCounterFailure : Failure() {

    /** */
    data class UnknownFailure(
        val message: String
    ) : DeleteCounterFailure()

}