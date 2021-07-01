package com.cornershop.counterstest.presentation.home.add_counter

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.AddCounterFragmentBinding
import com.cornershop.counterstest.presentation.common.extension.android.input_valitator.counter.CounterFormatError
import com.cornershop.counterstest.presentation.common.extension.android.input_valitator.counter.getMessage
import com.cornershop.counterstest.presentation.common.extension.android.showSnackBar
import com.cornershop.counterstest.presentation.common.extension.android.view.setClearErrorListenerOnType
import com.cornershop.counterstest.presentation.common.extension.android.view.setErrorMessage
import com.cornershop.counterstest.presentation.common.extension.failure_manage.getCommonFailureMessage
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressMenuItem.hideProgressMenuItem
import com.cornershop.counterstest.presentation.common.extension.loader.ProgressMenuItem.showProgressMenuItem
import com.cornershop.counterstest.presentation.common.extension.message.dialog.showCommonDialog
import com.example.counters.domain.use_case.add_counter.AddCounterFailure
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.presentation.add_counter.AddCounterStatus
import com.example.domain.Failure
import com.example.domain.presentation.Status
import kotlinx.android.synthetic.main.add_counter_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddCounterFragment : Fragment() {

    private val binding: AddCounterFragmentBinding by lazy {
        AddCounterFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@AddCounterFragment
            viewModel = addCounterViewModel
        }
    }

    /* */
    private lateinit var menu: Menu


    /* */
    private val addCounterViewModel: AddCounterViewModel by viewModel()

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
        setupTextChangeListener()
    }

    /** */
    private fun setupTextChangeListener() {
        binding.apply {
            tietCounter.setClearErrorListenerOnType(tilCounter)
        }
    }

    /** */
    private fun setupToolbar() {
        binding.mtToolbar.apply {
            setNavigationOnClickListener { onBackPressed() }
            menu.clear()
            inflateMenu(R.menu.menu_add_counter)
            setOnMenuItemClickListener(::onMenuItemClickListener)
            this@AddCounterFragment.menu = menu
        }
    }

    /** */
    private fun onMenuItemClickListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save_counter -> onSaveClickListener()
        }
        return true
    }

    /** */
    private fun onSaveClickListener() {
        if (addCounterViewModel.isValidCounterInformation())
            executeAddCounter()
        else
            manageInputFormatErrors()
    }

    /** */
    private fun manageInputFormatErrors() {
        binding.apply {
            tilCounter.setErrorMessage(
                CounterFormatError.getError(
                    addCounterViewModel.counter
                )?.getMessage(
                    requireContext()
                )
            )
        }
    }

    /** */
    private fun showProgress() {
        menu.findItem(R.id.action_save_counter)?.let { menuItem ->
            showProgressMenuItem(menuItem)
        }
    }

    /** */
    private fun hideProgress() {
        menu.findItem(R.id.action_save_counter)?.let {
            hideProgressMenuItem()
        }
    }

    /** */
    private fun executeAddCounter() {
        addCounterViewModel.addCountersAsLiveData()
            .observe(viewLifecycleOwner, createAddCountersStatusObserver())
    }

    /** */
    private fun createAddCountersStatusObserver() = Observer<AddCounterStatus> {
        hideProgress()
        when (it) {
            is Status.Loading -> showProgress()
            is Status.Failed -> manageAddCounterFailure(it.failure)
            is Status.Done -> manageAddCounterDone()
        }
    }

    /** */
    private fun manageAddCounterFailure(failure: Failure) {
        when (failure) {
            AddCounterFailure.NetworkConnectionFailure -> {
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
    private fun manageAddCounterDone() {
        showSnackBar(R.string.add_counter_text_counter_created_done)
        tiet_counter.text?.clear()
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }


}