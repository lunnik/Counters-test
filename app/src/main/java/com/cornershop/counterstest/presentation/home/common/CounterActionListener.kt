package com.cornershop.counterstest.presentation.home.common

import com.cornershop.counterstest.presentation.home.adapter.model.CounterModifier

interface CounterActionListener {

    fun onCounterLongClickListener(counterModifier: CounterModifier)

    /** */
    fun onDecreaseCounterClickListener(counterModifier: CounterModifier)

    /** */
    fun onIncreaseCounterClickListener(counterModifier: CounterModifier)

    /** */
    fun onDeleteActionListener(counterModifier: CounterModifier)
}