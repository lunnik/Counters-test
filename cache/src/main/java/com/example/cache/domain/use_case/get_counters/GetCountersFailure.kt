package com.example.cache.domain.use_case.get_counters

import com.example.domain.Failure

/** */
sealed class GetCountersFailure : Failure() {

    /** */
    data class UnknownFailure(
        val message: String
    ) : GetCountersFailure()

}