package com.example.counters.domain.use_case.get_counters

import com.example.counters.domain.entity.Counter

/** */
data class GetCountersResponse(
    val counters:List<Counter>
)
