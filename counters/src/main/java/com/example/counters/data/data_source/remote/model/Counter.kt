package com.example.counters.data.data_source.remote.model

import com.example.counters.data.data_source.remote.model.dto.CounterDto
import com.squareup.moshi.JsonClass

/** */
@JsonClass(generateAdapter = true)
internal  class CounterHttpResponse(
    val counters : List<CounterDto>
) 