package com.cornershop.counterstest.presentation.common.extension.android

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cornershop.counterstest.R
import com.example.domain.Failure

/** */
fun <T> Fragment.navigateTo(javaClass: Class<T>, clearTop: Boolean = false) =
    requireContext().navigateTo(javaClass, clearTop)

/** */
fun Fragment.getNetworkConnectionFailureMessage() =
    getString(R.string.network_connection_failure_message)

/** */
fun Fragment.getUnknownFailureMessage(failureMessage: String) =
    getString(R.string.failure_message, failureMessage)

/** */
fun <T> Fragment.showToast(value: T, length: Int = Toast.LENGTH_SHORT) =
    requireContext().showToast(value, length)

/** */
fun <T> Fragment.showLongToast(value: T) =
    requireContext().showLongToast(value)


/** */
fun Fragment.getFailureMessage(failure: Failure) =
    getString(R.string.failure_message, failure.toString())
