package com.example.cache.domain.use_case.get_counters_by_titile

import com.example.cache.domain.entity.Counter


/** */
data class GetCounterByTitleResponse(
    val counters: List<Counter>
)