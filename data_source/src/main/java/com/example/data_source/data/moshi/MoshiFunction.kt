package com.example.data_source.data.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 *
 */
inline fun <reified T> jsonToMoshiObject(value: String): T {
    val adapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(T::class.java)
    return adapter.fromJson(value) ?: error("${T::class.java.simpleName} format error")
}
