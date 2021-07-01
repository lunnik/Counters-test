package com.cornershop.counterstest.presentation.home.adapter

import androidx.recyclerview.widget.ListAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.cornershop.counterstest.presentation.home.adapter.model.CounterModifier
import com.cornershop.counterstest.presentation.home.common.CounterActionListener

class CountersAdapter(
    private val onActionClickListener: CounterActionListener
) : ListAdapter<CounterModifier, CounterViewHolder>(CounterDiffCallback()) {

    /** */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CounterViewHolder =
        CounterViewHolder.from(parent)

    /** */
    override fun onBindViewHolder(
        holder: CounterViewHolder,
        position: Int
    ) {
        val counter = currentList[position]
        holder.bind(counter, onActionClickListener)
    }
}


class CounterDiffCallback : DiffUtil.ItemCallback<CounterModifier>() {

    /** */
    override fun areItemsTheSame(
        oldItem: CounterModifier,
        newItem: CounterModifier
    ): Boolean = oldItem.id == newItem.id

    /** */
    override fun areContentsTheSame(
        oldItem: CounterModifier,
        newItem: CounterModifier,
    ): Boolean = oldItem.equals(newItem)

}