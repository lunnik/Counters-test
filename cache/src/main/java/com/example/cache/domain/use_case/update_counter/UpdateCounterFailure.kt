package com.example.cache.domain.use_case.update_counter

import com.example.domain.Failure

/** */
sealed class UpdateCounterFailure : Failure() {

    /** */
    data class UnknownFailure(
        val message: String
    ) : UpdateCounterFailure()

}