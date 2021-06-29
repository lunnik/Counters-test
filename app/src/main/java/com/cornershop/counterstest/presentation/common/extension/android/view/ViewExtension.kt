package com.cornershop.counterstest.presentation.common.extension.android.view

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 *
 */
fun View?.visible() {
    this?.visibility = View.VISIBLE
}

/**
 *
 */
fun View?.gone() {
    this?.visibility = View.GONE
}


/**
 * Presents [Snackbar] with the given message.
 * You must to add to end line '.show' to present this instance.
 */
fun View.presentShortSnackbar(message: String): Snackbar =
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT)

