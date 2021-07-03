package com.example.cache.domain.use_case.get_counters_by_titile

import com.example.domain.Failure

/** */
sealed class GetCounterByTitleFailure : Failure() {

    /** */
    data class UnknownFailure(
        val message: String
    ) : GetCounterByTitleFailure()

}