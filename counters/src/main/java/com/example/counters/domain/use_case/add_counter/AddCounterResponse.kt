package com.example.counters.domain.use_case.add_counter

import com.example.counters.domain.entity.Counter

/** */
data class AddCounterResponse(
    val counters:List<Counter>
)
