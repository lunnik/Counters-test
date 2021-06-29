package com.example.counters.domain.use_case.delete_counter

import com.example.data_source.data.failure.DefaultFailure
import com.example.data_source.data.remote.model.failure.HttpFailure
import com.example.data_source.data.remote.model.failure.NetworkFailure
import com.example.domain.Failure


/** */
sealed class DeleteCounterFailure : Failure() {

    /** */
    object NetworkConnectionFailure : DeleteCounterFailure(), NetworkFailure

    /** */
    object NotEnoughPrivilegesFailure : DeleteCounterFailure()

    /** */
    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : DeleteCounterFailure(), HttpFailure

    /** */
    data class UnknownFailure(
        override val message: String
    ) : DeleteCounterFailure(), DefaultFailure

}