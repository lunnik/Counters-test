package com.example.counters.domain.use_case.decrease_counter

import com.example.data_source.data.failure.DefaultFailure
import com.example.data_source.data.remote.model.failure.HttpFailure
import com.example.data_source.data.remote.model.failure.NetworkFailure
import com.example.domain.Failure


/** */
sealed class DecreaseCounterFailure : Failure() {

    /** */
    object NetworkConnectionFailure : DecreaseCounterFailure(), NetworkFailure

    /** */
    object NotEnoughPrivilegesFailure : DecreaseCounterFailure()

    /** */
    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : DecreaseCounterFailure(), HttpFailure

    /** */
    data class UnknownFailure(
        override val message: String
    ) : DecreaseCounterFailure(), DefaultFailure

}