package com.cornershop.counterstest.presentation.home.adapter

import androidx.recyclerview.widget.ListAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.cornershop.counterstest.presentation.home.common.CounterActionListener
import com.example.cache.domain.entity.Counter

class CountersAdapter(
    private val onActionClickListener: CounterActionListener
) : ListAdapter<Counter, CounterViewHolder>(CounterDiffCallback()) {

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
        val news = currentList[position]
        holder.bind(news, onActionClickListener)
    }
}


class CounterDiffCallback : DiffUtil.ItemCallback<Counter>() {

    /** */
    override fun areItemsTheSame(
        oldItem: Counter,
        newItem: Counter
    ): Boolean = oldItem.id == newItem.id

    /** */
    override fun areContentsTheSame(
        oldItem: Counter,
        newItem: Counter,
    ): Boolean = oldItem.equals(newItem)

}