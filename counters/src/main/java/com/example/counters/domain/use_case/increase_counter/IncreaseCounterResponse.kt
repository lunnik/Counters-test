package com.example.counters.domain.use_case.increase_counter

import com.example.counters.domain.entity.Counter

/** */
data class IncreaseCounterResponse(
    val counters:List<Counter>
)
