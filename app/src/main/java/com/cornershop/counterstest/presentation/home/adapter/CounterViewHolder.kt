package com.cornershop.counterstest.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cornershop.counterstest.databinding.ViewHolderCountersBinding
import com.cornershop.counterstest.presentation.home.common.CounterActionListener
import com.example.counters.domain.entity.Counter

class CounterViewHolder(
    var binding: ViewHolderCountersBinding
) : RecyclerView.ViewHolder(binding.root) {

    /* */
    private val context: Context get() = binding.root.context

    /** */
    fun bind(
        counter: Counter,
        onActionClickListener: CounterActionListener
    ) {
        binding.counter = counter
        binding.root.setOnClickListener {

        }
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