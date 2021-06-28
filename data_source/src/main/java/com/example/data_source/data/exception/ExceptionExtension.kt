package com.example.data_source.data.exception

/* */
val Exception.tag: String get() = javaClass.simpleName

/**
 *
 */
fun Exception.message(): String = message ?: tag
