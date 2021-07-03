package com.cornershop.counterstest.presentation.home.adapter.model

import com.example.cache.domain.entity.Counter

/** */
data class CounterModifier(
    val id: String,
    val count: Int,
    val title: String,
    var isSelected: Boolean
) {

    /** */
    fun toCounter() = Counter(id, count, title)

    /** */
    companion object {

        /** */
        fun fromCounter(it: Counter) = CounterModifier(
            id = it.id,
            count = it.count,
            title = it.title,
            isSelected = false
        )

    }
}