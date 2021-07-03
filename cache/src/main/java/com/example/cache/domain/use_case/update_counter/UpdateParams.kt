package com.example.cache.domain.use_case.update_counter

import com.example.cache.domain.entity.Counter


/** */
data class UpdateParams(
    val counters: Counter
)