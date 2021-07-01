package com.cornershop.counterstest.presentation.common.extension.failure_manage

import android.content.Context
import androidx.fragment.app.Fragment
import com.cornershop.counterstest.R
import com.example.data_source.data.failure.DefaultFailure
import com.example.data_source.data.remote.model.failure.HttpFailure
import com.example.data_source.data.remote.model.failure.NetworkFailure
import com.example.domain.Failure


/**
 *
 */
fun Context.getNetworkConnectionFailureMessage() =
    getString(R.string.connection_error_description)

/**
 *
 */
fun Context.getServerFailure(httpFailure: HttpFailure) =
    getString(R.string.failure_http_code, httpFailure.code, httpFailure.message)

/**
 *
 */
fun Context.getUnknownFailureMessage(message: String) =
    getString(R.string.failure_unknown, message)



/**
 *
 */
fun Fragment.getCommonFailureMessage(failure: Failure): String =
    requireContext().getCommonFailureMessage(failure)

/**
 *
 */
fun Context.getCommonFailureMessage(failure: Failure): String =
    when (failure) {
        is NetworkFailure -> getNetworkConnectionFailureMessage()
        is HttpFailure -> getServerFailure(failure)
        is DefaultFailure -> getUnknownFailureMessage(failure.message)
        else -> getUnknownFailureMessage(failure.javaClass.simpleName)
    }