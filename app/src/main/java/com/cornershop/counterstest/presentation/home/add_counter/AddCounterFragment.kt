package com.cornershop.counterstest.presentation.home.add_counter

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.AddCounterFragmentBinding
import com.cornershop.counterstest.presentation.common.extension.android.showSnackbar
import com.cornershop.counterstest.presentation.common.extension.failure_manage.getCommonFailureMessage
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressDialog.hideProgressDialog
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressDialog.showProgressDialog
import com.example.counters.domain.entity.Counter
import com.example.counters.presentation.add_counter.AddCounterStatus
import com.example.domain.Failure
import com.example.domain.presentation.Status
import org.koin.android.viewmodel.ext.android.viewModel

class AddCounterFragment : Fragment() {

    private val binding: AddCounterFragmentBinding by lazy {
        AddCounterFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@AddCounterFragment
            viewModel = adCounterViewModel
        }
    }

    /* */
    private lateinit var menu: Menu


    /* */
    private val adCounterViewModel: AddCounterViewModel by viewModel()
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
    }

    /** */
     private fun setupToolbar() {
        binding.mtToolbar.apply {
            menu.clear()
            inflateMenu(R.menu.menu_add_counter)
            setOnMenuItemClickListener(::onMenuItemClickListener)
            this@AddCounterFragment.menu = menu
        }
    }

    /** */
    private fun onMenuItemClickListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save_counter -> execute()
        }
        return true
    }

    /** */
    private fun execute() {
        adCounterViewModel.addCountersAsLiveData()
            .observe(viewLifecycleOwner, createAddCountersStatusObserver())
    }

    /** */
    private fun createAddCountersStatusObserver() = Observer<AddCounterStatus> {
        hideProgressDialog()
        when (it) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageAddCounterFailure(it.failure)
            is Status.Done -> manageAddCounterDone(it.value.counters)
        }
    }

    /** */
    private fun manageAddCounterFailure(failure: Failure) {
        val message = getCommonFailureMessage(failure)
        showSnackbar(message)
    }

    /** */
    private fun manageAddCounterDone(counter: List<Counter>) {


    }


}