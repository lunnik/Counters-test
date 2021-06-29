package com.example.counters.domain.use_case.add_counter

import com.example.data_source.data.failure.DefaultFailure
import com.example.data_source.data.remote.model.failure.HttpFailure
import com.example.data_source.data.remote.model.failure.NetworkFailure
import com.example.domain.Failure


/** */
sealed class AddCounterFailure : Failure() {

    /** */
    object NetworkConnectionFailure : AddCounterFailure(), NetworkFailure

    /** */
    object NotEnoughPrivilegesFailure : AddCounterFailure()

    /** */
    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : AddCounterFailure(), HttpFailure

    /** */
    data class UnknownFailure(
        override val message: String
    ) : AddCounterFailure(), DefaultFailure

}