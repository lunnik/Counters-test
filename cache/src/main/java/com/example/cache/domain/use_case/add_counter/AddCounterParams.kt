package com.example.cache.domain.use_case.add_counter

import com.example.cache.domain.entity.Counter


/** */
data class AddCounterParams(
    val counters: List<Counter>
)