package com.example.counters.data.data_source.remote

import com.example.counters.domain.use_case.add_counter.AddCounterFailure
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.data_source.data.remote.model.HttpErrorCode
import com.example.data_source.data.remote.model.errorMessage
import retrofit2.HttpException

/**
 *
 */
internal fun HttpException.toGetCountersFailure(): GetCountersFailure =
    when (HttpErrorCode.fromCode(code())) {
        HttpErrorCode.FORBIDDEN -> GetCountersFailure.NotEnoughPrivilegesFailure
        else -> GetCountersFailure.ServerFailure(code(), errorMessage())
    }

/** */
internal fun HttpException.toAddCounterFailure(): AddCounterFailure =
    when (HttpErrorCode.fromCode(code())) {
        HttpErrorCode.FORBIDDEN -> AddCounterFailure.NotEnoughPrivilegesFailure
        else -> AddCounterFailure.ServerFailure(code(), errorMessage())
    }
