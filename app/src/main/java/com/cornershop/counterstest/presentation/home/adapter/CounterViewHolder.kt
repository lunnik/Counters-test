package com.cornershop.counterstest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cornershop.counterstest.databinding.ViewHolderCountersBinding
import com.cornershop.counterstest.presentation.home.adapter.model.CounterModifier
import com.cornershop.counterstest.presentation.home.common.CounterActionListener

class CounterViewHolder(
    var binding: ViewHolderCountersBinding
) : RecyclerView.ViewHolder(binding.root) {


    /** */
    fun bind(
        counterModifier: CounterModifier,
        onActionClickListener: CounterActionListener
    ) {
        binding.counter = counterModifier
        binding.imageButtonMinus.setOnClickListener {
            onMinusClickListener(counterModifier, onActionClickListener)
        }
        binding.imageButtonPlus.setOnClickListener {
            onPlusClickListener(counterModifier, onActionClickListener)
        }
    }

    /** */
    private fun onPlusClickListener(
        counterModifier: CounterModifier,
        onActionClickListener: CounterActionListener?
    ) {
        counterModifier.count + 1
        onActionClickListener?.onIncreaseCounterClickListener(counterModifier)
    }

    /** */
    private fun onMinusClickListener(
        counterModifier: CounterModifier,
        onActionClickListener: CounterActionListener?
    ) {
        if (counterModifier.count == 0)
            onDeleteActionListener(counterModifier, onActionClickListener)
        counterModifier.count - 1
        onActionClickListener?.onDecreaseCounterClickListener(counterModifier)
    }

    /** */
    private fun onDeleteActionListener(
        counterModifier: CounterModifier,
        onActionClickListener: CounterActionListener?
    ) {
        onActionClickListener?.onDeleteActionListener(counterModifier)
    }

    /** */
    companion object {

        /** */
        fun from(parent: ViewGroup): CounterViewHolder {
            val binding = ViewHolderCountersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CounterViewHolder(binding)
        }

    }

}