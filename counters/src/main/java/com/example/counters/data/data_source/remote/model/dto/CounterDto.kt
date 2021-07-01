package com.example.counters.data.data_source.remote.model.dto


import com.example.cache.domain.entity.Counter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/** */
@JsonClass(generateAdapter = true)
internal data class CounterDto(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String
) {

    /** */
    fun toCounter() = Counter(
        count = count,
        id = id,
        title = title
    )
}