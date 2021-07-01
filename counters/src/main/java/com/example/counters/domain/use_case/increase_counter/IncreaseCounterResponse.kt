package com.example.counters.domain.use_case.increase_counter

import com.example.cache.domain.entity.Counter


/** */
data class IncreaseCounterResponse(
    val counters:List<Counter>
)
