package com.cornershop.counterstest.presentation.home.common

import com.cornershop.counterstest.presentation.home.adapter.model.CounterModifier

interface CounterActionListener {

    /** */
    fun onMinusClickListener(counterModifier: CounterModifier)

    /** */
    fun onPlusClickListener(counterModifier: CounterModifier)

    /** */
    fun onDeleteActionListener()
}