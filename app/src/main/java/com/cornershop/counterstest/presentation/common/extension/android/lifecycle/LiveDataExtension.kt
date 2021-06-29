package com.cornershop.counterstest.presentation.common.extension.android.lifecycle

import androidx.lifecycle.LiveData

/* */
val <T> LiveData<T>.valueOrError: T
    get() = value ?: error("Value in ${javaClass.simpleName} is null")