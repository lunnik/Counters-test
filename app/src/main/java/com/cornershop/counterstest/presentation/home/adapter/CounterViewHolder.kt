package com.cornershop.counterstest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.ViewHolderCountersBinding
import com.cornershop.counterstest.presentation.common.extension.android.view.gone
import com.cornershop.counterstest.presentation.common.extension.android.view.visible
import com.cornershop.counterstest.presentation.home.adapter.model.CounterModifier
import com.cornershop.counterstest.presentation.home.common.CounterActionListener

class CounterViewHolder(
    var binding: ViewHolderCountersBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    /** */
    fun bind(
        counterModifier: CounterModifier,
        onActionClickListener: CounterActionListener
    ) {
        binding.counter = counterModifier
        isSelectItem(counterModifier.isSelected)
        binding.imageButtonMinus.setOnClickListener {
            onMinusClickListener(counterModifier, onActionClickListener)
        }
        binding.imageButtonPlus.setOnClickListener {
            onPlusClickListener(counterModifier, onActionClickListener)
        }
        binding.root.setOnLongClickListener{
            onActionClickListener.onCounterClickListener(counterModifier)
            true
        }
    }

    /** */
    private fun isSelectItem(isSelected: Boolean) {
        if (isSelected) {
            binding.linearLayoutRoot.background =
                context.getDrawable(R.drawable.ic_background_item_selected)
            binding.contentControlCounters.gone()
            binding.imageViewCheck.visible()
        } else {
            binding.linearLayoutRoot.background = null
            binding.contentControlCounters.visible()
            binding.imageViewCheck.gone()
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
        if (counterModifier.count == 0) {
            onDeleteActionListener(counterModifier, onActionClickListener)
            return
        }
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