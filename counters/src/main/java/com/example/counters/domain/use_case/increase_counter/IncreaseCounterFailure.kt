package com.example.counters.domain.use_case.increase_counter

import com.example.data_source.data.failure.DefaultFailure
import com.example.data_source.data.remote.model.failure.HttpFailure
import com.example.data_source.data.remote.model.failure.NetworkFailure
import com.example.domain.Failure


/** */
sealed class IncreaseCounterFailure : Failure() {

    /** */
    object NetworkConnectionFailure : IncreaseCounterFailure(), NetworkFailure

    /** */
    object NotEnoughPrivilegesFailure : IncreaseCounterFailure()

    /** */
    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : IncreaseCounterFailure(), HttpFailure

    /** */
    data class UnknownFailure(
        override val message: String
    ) : IncreaseCounterFailure(), DefaultFailure

}