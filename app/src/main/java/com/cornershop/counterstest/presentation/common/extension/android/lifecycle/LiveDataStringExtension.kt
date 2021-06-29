package com.cornershop.counterstest.presentation.common.extension.android.lifecycle

import androidx.lifecycle.LiveData
import com.example.data_source.data.extension.empty

/* */
val LiveData<String>.valueOrEmpty get() = value?.trim() ?: String.empty
