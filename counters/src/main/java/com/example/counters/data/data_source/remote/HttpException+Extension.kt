package com.example.counters.data.data_source.remote

import com.example.counters.domain.use_case.add_counter.AddCounterFailure
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterFailure
import com.example.counters.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterFailure
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

/** */
internal fun HttpException.toIncreaseCounterFailure(): IncreaseCounterFailure =
    when (HttpErrorCode.fromCode(code())) {
        HttpErrorCode.FORBIDDEN -> IncreaseCounterFailure.NotEnoughPrivilegesFailure
        else -> IncreaseCounterFailure.ServerFailure(code(), errorMessage())
    }


/** */
internal fun HttpException.toDecreaseCounterFailure(): DecreaseCounterFailure =
    when (HttpErrorCode.fromCode(code())) {
        HttpErrorCode.FORBIDDEN -> DecreaseCounterFailure.NotEnoughPrivilegesFailure
        else -> DecreaseCounterFailure.ServerFailure(code(), errorMessage())
    }

/** */
internal fun HttpException.toDeleteCounterFailure(): DeleteCounterFailure =
    when (HttpErrorCode.fromCode(code())) {
        HttpErrorCode.FORBIDDEN -> DeleteCounterFailure.NotEnoughPrivilegesFailure
        else -> DeleteCounterFailure.ServerFailure(code(), errorMessage())
    }
