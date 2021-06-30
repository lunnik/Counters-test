package com.cornershop.counterstest.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.HomeFragmentBinding
import com.cornershop.counterstest.presentation.common.extension.android.showSnackbar
import com.cornershop.counterstest.presentation.common.extension.android.view.gone
import com.cornershop.counterstest.presentation.common.extension.android.view.visible
import com.cornershop.counterstest.presentation.common.extension.failure_manage.getCommonFailureMessage
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressDialog.hideProgressDialog
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressDialog.showProgressDialog
import com.example.counters.domain.entity.Counter
import com.example.counters.presentation.get_counters.GetCountersStatus
import com.example.domain.Failure
import com.example.domain.presentation.Status
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
    private fun manageGetCountersFailure(failure: Failure) {
        val message = getCommonFailureMessage(failure)
        showSnackbar(message)
    }

    /** */
    private fun manageGetCountersDone(counter: List<Counter>) {
        if (counter.isEmpty())
            binding.includeLayoutNoContent.visible()
        else
            binding.includeLayoutNoContent.gone()
    }

}