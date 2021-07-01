package com.cornershop.counterstest.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.HomeFragmentBinding
import com.cornershop.counterstest.presentation.common.extension.android.showSnackBar
import com.cornershop.counterstest.presentation.common.extension.android.view.gone
import com.cornershop.counterstest.presentation.common.extension.android.view.visible
import com.cornershop.counterstest.presentation.common.extension.failure_manage.getCommonFailureMessage
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressDialog.hideProgressDialog
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressDialog.showProgressDialog
import com.cornershop.counterstest.presentation.common.extension.message.dialog.showCommonDialog
import com.cornershop.counterstest.presentation.home.adapter.CountersAdapter
import com.cornershop.counterstest.presentation.home.adapter.model.CounterModifier
import com.cornershop.counterstest.presentation.home.common.CounterActionListener
import com.example.cache.domain.entity.Counter
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.presentation.get_counters.GetCountersStatus
import com.example.domain.presentation.Status
import kotlinx.android.synthetic.main.layout_counter_content.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    /* */
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@HomeFragment
        }
    }

    /* */
    private val homeViewModel: HomeViewModel by viewModel()

    /* */
    private lateinit var countersAdapter: CountersAdapter

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        execute()
        setupView()
    }

    /** */
    private fun setupView() {
        setupAction()
        initRecyclerView()
    }

    /** */
    private fun setupAction() {
        binding.actionButtonAddCounters.setOnClickListener(::onActionAddCounterClickListener)
    }

    /** */
    private fun onActionAddCounterClickListener(v: View) {
        findNavController().navigate(R.id.action_homeFragment_to_addCounterFragment)
    }


    /** */
    private fun initRecyclerView() {
        countersAdapter = CountersAdapter(counterActionListener)
        binding.includeLayoutContent.recycler_view_counter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.includeLayoutContent.recycler_view_counter.adapter = countersAdapter
    }

    /** */
    private val counterActionListener = object : CounterActionListener {
        override fun onMinusClickListener(counterModifier: CounterModifier) {
            //TODO("Not yet implemented")
        }

        override fun onPlusClickListener(counterModifier: CounterModifier) {
            // TODO("Not yet implemented")
        }

        override fun onDeleteActionListener() {
            //TODO("Not yet implemented")
        }
    }


    /** */
    private fun execute() {
        homeViewModel.getCountersAsLiveData()
            .observe(viewLifecycleOwner, createGetCountersStatusObserver())
    }

    /** */
    private fun createGetCountersStatusObserver() = Observer<GetCountersStatus> {
        hideProgressDialog()
        when (it) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageGetCountersFailure(it.failure)
            is Status.Done -> manageGetCountersDone(it.value.counters)
        }
    }

    /** */
    private fun manageGetCountersFailure(failure: GetCountersFailure) {
        when (failure) {
            GetCountersFailure.NetworkConnectionFailure -> {
                showCommonDialog(positiveAction = ::positiveActionDialog)
            }
            else -> {
                val message = getCommonFailureMessage(failure)
                showSnackBar(message)
            }
        }

    }

    private fun positiveActionDialog() {
        /*NOTHING*/
    }

    /** */
    private fun manageGetCountersDone(counter: List<Counter>) {
        if (counter.isEmpty())
            binding.includeLayoutNoContent.visible()
        else {
            val list = counter.map { CounterModifier.fromCounter(it) }
            countersAdapter.submitList(list)
            binding.includeLayoutNoContent.gone()
            binding.includeLayoutContent.visible()
        }
    }

}