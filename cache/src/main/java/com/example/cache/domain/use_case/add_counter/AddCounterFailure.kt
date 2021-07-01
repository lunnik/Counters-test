package com.example.cache.domain.use_case.add_counter

import com.example.domain.Failure

/** */
sealed class AddCounterFailure : Failure() {

    /** */
    data class UnknownFailure(
        val message: String
    ) : AddCounterFailure()

}