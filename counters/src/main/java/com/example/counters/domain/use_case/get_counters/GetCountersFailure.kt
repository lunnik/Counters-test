package com.example.counters.domain.use_case.get_counters

import com.example.data_source.data.failure.DefaultFailure
import com.example.data_source.data.remote.model.failure.HttpFailure
import com.example.data_source.data.remote.model.failure.NetworkFailure
import com.example.domain.Failure


/** */
sealed class GetCountersFailure : Failure() {

    /** */
    object NetworkConnectionFailure : GetCountersFailure(), NetworkFailure

    /** */
    object NotEnoughPrivilegesFailure : GetCountersFailure()

    /** */
    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : GetCountersFailure(), HttpFailure

    /** */
    data class UnknownFailure(
        override val message: String
    ) : GetCountersFailure(), DefaultFailure

}