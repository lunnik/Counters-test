package com.cornershop.counterstest.presentation.common.extension.android.input_valitator

/** */
interface Validator<T> {

    /**
     * Indies if the value received as param is associated to error.
     * @param value to verify
     * @return [Boolean] <code>true</code> if the value is associated to error. <code>false</code>
     * in other case.
     */
    fun isValidValue(value: T) : Boolean

}