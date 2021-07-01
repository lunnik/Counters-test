package com.example.counters.domain.use_case.decrease_counter

import com.example.cache.domain.entity.Counter

/** */
data class DecreaseCounterResponse(
    val counters:List<Counter>
)
