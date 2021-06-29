package com.example.counters.data.data_source.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class DecreaseCounterRequest(
    @Json(name = "id") val id: String
)