package com.cornershop.counterstest.presentation.common.extension.android.input_valitator.counter

import android.content.Context
import com.cornershop.counterstest.R


/** */
fun CounterFormatError.getMessage(context: Context): String =
    when (this) {
        CounterFormatError.MIN_LENGTH_ERROR ->
            context.getString(
                R.string.counter_format_error_min_length,
                context.resources.getInteger(R.integer.counter_min_length)
            )
    }
