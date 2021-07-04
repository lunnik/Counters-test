package com.cornershop.counterstest.presentation.home.create_counter_example

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.CreateCounterExampleFragmentBinding
import com.cornershop.counterstest.presentation.home.add_counter.AddCounterFragment.Companion.BUNDLE_KEY_COUNTER_TITLE
import com.cornershop.counterstest.presentation.home.add_counter.AddCounterFragment.Companion.COUNTER_KEY_RESULT
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class CreateCounterExampleFragment : Fragment() {
    /* */
    private val binding: CreateCounterExampleFragmentBinding by lazy {
        CreateCounterExampleFragmentBinding.inflate(layoutInflater)
    }

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    /** */
    private fun setupView() {
        setupToolbar()
        val drinks = resources.getStringArray(R.array.drinks_array)
        val foods = resources.getStringArray(R.array.food_array)
        val misc = resources.getStringArray(R.array.misc_array)
        createChipGroup(binding.chipGroupDrinks, drinks)
        createChipGroup(binding.chipGroupFoods, foods)
        createChipGroup(binding.chipGroupMisc, misc)
    }

    /** */
    private fun setupToolbar() {
        binding.mtToolbar.apply {
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    /** */
    private fun createChipGroup(chipGroup: ChipGroup, drinks: Array<String>) {
        drinks.forEach {
            val chip = Chip(requireContext())
            chip.apply {
                text = it
                setChipBackgroundColorResource(R.color.light_gray)
                isCloseIconVisible = false
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                tag = it
                setOnClickListener(onCounterClickListener)
            }

            chipGroup.addView(chip)
        }

    }

    /** */
    private val onCounterClickListener = View.OnClickListener {
        if (it.tag is String) {
            val title = it.tag as String
            setFragmentResult(COUNTER_KEY_RESULT, bundleOf(BUNDLE_KEY_COUNTER_TITLE to title))
            findNavController().popBackStack()
        }

    }

    /** */
    private fun onBackPressed() {
        findNavController().popBackStack()
    }


}