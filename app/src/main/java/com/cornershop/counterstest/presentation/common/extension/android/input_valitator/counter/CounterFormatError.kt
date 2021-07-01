package com.cornershop.counterstest.presentation.common.extension.android.input_valitator.counter
import com.cornershop.counterstest.presentation.common.extension.android.input_valitator.Validator

enum class CounterFormatError : Validator<String> {

    MIN_LENGTH_ERROR {
        override fun isValidValue(value: String) = value.count() > 0
    };

    /** */
    companion object {
        /** */
        fun isValidCounter(name: String) = getError(name) == null

        /** */
        fun getError(counter: String) = values().firstOrNull { !it.isValidValue(counter) }

    }
}