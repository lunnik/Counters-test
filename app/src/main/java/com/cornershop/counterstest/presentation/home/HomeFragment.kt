package com.cornershop.counterstest.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleFailure
import com.example.cache.presentation.get_counters_by_title.GetCountersByTitleStatus
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterFailure
import com.example.counters.domain.use_case.delete_counter.DeleteCounterFailure
import com.example.counters.domain.use_case.get_counters.GetCountersFailure
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterFailure
import com.example.counters.presentation.decrease_counter.DecreaseCountersStatus
import com.example.counters.presentation.delete_counter.DeleteCountersStatus
import com.example.counters.presentation.get_counters.GetCountersStatus
import com.example.counters.presentation.increase_counter.IncreaseCountersStatus
import com.example.domain.presentation.Status
import com.example.network.internet_connection.InternetConnectionRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.layout_counter_content.view.*
import kotlinx.android.synthetic.main.layout_counter_error.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class HomeFragment : Fragment(), KoinComponent {

    /* */
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@HomeFragment
        }
    }

    /* */
    private val internetConnectionRepository: InternetConnectionRepository by inject()

    /* */
    private val homeViewModel: HomeViewModel by viewModel()

    /* */
    private lateinit var countersAdapter: CountersAdapter

    /* */
    private var actionMode: ActionMode? = null

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
        setupPullToRefreshCounters()
        setupToolbar()
        setupAction()
        setupSearchView()
        initRecyclerView()
    }

    /** */
    private fun setupPullToRefreshCounters() {
        binding.swipeRefreshCounters.setOnRefreshListener(::execute)
    }

    /** */
    private fun setupSearchView() {
        binding.searchViewCounter.setOnQueryTextFocusChangeListener(onQueryTextFocusChangeListener)
        binding.searchViewCounter.setOnQueryTextListener(onQueryTextListener)
    }

    private val onQueryTextFocusChangeListener =
        View.OnFocusChangeListener { v, hasFocus ->

        }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        /** */
        override fun onQueryTextSubmit(query: String?): Boolean {

            return true
        }

        /** */
        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                if (it.isNotEmpty()) {
                    val filteredCounters = homeViewModel.search(it)
                    if (filteredCounters.isEmpty())
                        binding.includeLayoutNoResult.visible()
                    countersAdapter.submitList(filteredCounters)
                    countersAdapter.notifyDataSetChanged()
                } else {
                    binding.includeLayoutNoResult.gone()
                    countersAdapter.submitList(homeViewModel.oldFilteredCounters)
                    countersAdapter.notifyDataSetChanged()
                }

            }
            return true
        }
    }

    /** */
    private fun setupToolbar() {
        binding.appBar.apply {
            outlineProvider = null
        }
    }

    /** */
    private fun setupAction() {
        binding.actionButtonAddCounters.setOnClickListener(::onActionAddCounterClickListener)
        binding.includeLayoutError.text_view_retry.setOnClickListener(::onrRetryCounterClickListener)
    }

    /** */
    private fun onActionAddCounterClickListener(v: View) {
        actionMode?.finish()
        findNavController().navigate(R.id.action_homeFragment_to_addCounterFragment)
    }

    /** */
    private fun onrRetryCounterClickListener(v: View) = execute()


    /** */
    private fun initRecyclerView() {
        countersAdapter = CountersAdapter(counterActionListener)
        binding.includeLayoutContent.recycler_view_counter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.includeLayoutContent.recycler_view_counter.adapter = countersAdapter
    }

    /** */
    private val counterActionListener = object : CounterActionListener {
        /** */
        override fun onCounterClickListener(counterModifier: CounterModifier) {
            setupMenuActionCounter(counterModifier)
        }

        /** */
        override fun onDecreaseCounterClickListener(counterModifier: CounterModifier) {
            executeDecreaseCounter(counterModifier.id)
        }

        /** */
        override fun onIncreaseCounterClickListener(counterModifier: CounterModifier) {
            if (internetConnectionRepository.isOnline) {
                executeIncreaseCounter(counterModifier.id)
            } else
                modifierCounterDialogAlert(counterModifier.toCounter())
        }

        /** */
        override fun onDeleteActionListener(counterModifier: CounterModifier) {
            deleteCounterDialogAlert(counterModifier.title, counterModifier.id)
        }
    }

    /** */
    private fun setupMenuActionCounter(counterModifier: CounterModifier) {
        val index = countersAdapter.currentList.indexOf(counterModifier)
        val item = countersAdapter.currentList[index]
        item.isSelected = !item.isSelected
        countersAdapter.notifyItemChanged(index)
        setupActionMode()

    }

    private fun setupActionMode() {
        val counters = countersAdapter.currentList.filter { it.isSelected }
        if (actionMode == null) actionMode = requireActivity().startActionMode(ActionModeCallback())
        if (counters.isNotEmpty()) actionMode?.title = getString(R.string.n_selected, counters.size)
        else actionMode?.finish()
    }

    //INCREMENT COUNTER
    /** */
    private fun executeIncreaseCounter(id: String) {
        homeViewModel.increaseCountersAsLiveData(id)
            .observe(viewLifecycleOwner, createIncreaseCounterObserver())
    }

    /** */
    private fun createIncreaseCounterObserver() = Observer<IncreaseCountersStatus> {
        hideProgressDialog()
        when (it) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageIncreaseCounterFailure(it.failure)
            is Status.Done -> manageGetCountersDone(it.value.counters)
        }
    }

    /** */
    private fun manageIncreaseCounterFailure(failure: IncreaseCounterFailure) {
        when (failure) {
            IncreaseCounterFailure.NetworkConnectionFailure -> {
                showCommonDialog(positiveAction = ::positiveActionDialog)
            }
            else -> {
                val message = getCommonFailureMessage(failure)
                showSnackBar(message)
            }
        }

    }

    //DECREMENT COUNTER

    /** */
    private fun executeDecreaseCounter(id: String) {
        homeViewModel.decreaseCountersAsLiveData(id)
            .observe(viewLifecycleOwner, createDecreaseCounterObserver())
    }

    /** */
    private fun createDecreaseCounterObserver() = Observer<DecreaseCountersStatus> {
        hideProgressDialog()
        when (it) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageDecreaseCounterFailure(it.failure)
            is Status.Done -> manageGetCountersDone(it.value.counters)
        }
    }

    /** */
    private fun manageDecreaseCounterFailure(failure: DecreaseCounterFailure) {
        when (failure) {
            DecreaseCounterFailure.NetworkConnectionFailure -> {
                showCommonDialog(positiveAction = ::positiveActionDialog)
            }
            else -> {
                val message = getCommonFailureMessage(failure)
                showSnackBar(message)
            }
        }

    }

    //LOAD  COUNTERS

    /** */
    private fun execute() {
        homeViewModel.getCountersAsLiveData()
            .observe(viewLifecycleOwner, createGetCountersStatusObserver())
    }

    /** */
    private fun createGetCountersStatusObserver() = Observer<GetCountersStatus> {
        hideProgress()
        when (it) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageGetCountersFailure(it.failure)
            is Status.Done -> manageGetCountersDone(it.value.counters)
        }
    }

    /** */
    private fun hideProgress() {
        actionMode = null
        binding.swipeRefreshCounters.isRefreshing = false
        hideProgressDialog()
    }

    /** */
    private fun manageGetCountersFailure(failure: GetCountersFailure) {
        when (failure) {
            GetCountersFailure.NetworkConnectionFailure -> {
                showCommonDialog(positiveAction = ::positiveActionDialog)
            }
            else -> {
                binding.includeLayoutError.visible()
                val message = getCommonFailureMessage(failure)
                showSnackBar(message)
            }
        }

    }

    /** */
    private fun manageGetCountersDone(counter: List<Counter>) {
        binding.includeLayoutError.gone()
        if (counter.isEmpty())
            binding.includeLayoutNoContent.visible()
        else {
            val listCounter = counter.map { CounterModifier.fromCounter(it) }
            homeViewModel.oldFilteredCounters.clear()
            homeViewModel.oldFilteredCounters.addAll(listCounter)
            countersAdapter.submitList(listCounter)
            binding.includeLayoutNoContent.gone()
            binding.includeLayoutContent.visible()
            val totalItems = listCounter.size
            var totalTime = 0
            listCounter.forEach {
                totalTime += it.count
            }
            binding.includeLayoutContent.text_view_items.text =
                getString(R.string.n_items, totalItems)
            binding.includeLayoutContent.text_view_items_time.text =
                getString(R.string.n_times, totalTime)
        }
    }

    //DELETE COUNTER

    /** */
    private fun executeDeleteCounter(id: String) {
        homeViewModel.deleteCountersAsLiveData(id)
            .observe(viewLifecycleOwner, createDeleteCounterObserver())
    }

    /** */
    private fun createDeleteCounterObserver() = Observer<DeleteCountersStatus> {
        hideProgressDialog()
        when (it) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageDeleteCounterFailure(it.failure)
            is Status.Done -> manageGetCountersDone(it.value.counters)
        }
    }

    /** */
    private fun manageDeleteCounterFailure(failure: DeleteCounterFailure) {
        when (failure) {
            DeleteCounterFailure.NetworkConnectionFailure -> {
                showCommonDialog(positiveAction = ::positiveActionDialog)
            }
            else -> {
                val message = getCommonFailureMessage(failure)
                showSnackBar(message)
            }
        }

    }

    /** */
    private fun positiveActionDialog() {
        /*NOTHING*/
    }

    //MENU ACTION MODE

    /** */
    inner class ActionModeCallback : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.action_delete -> {
                    deleteCounterByToolbar()
                    return true
                }

                R.id.action_shared -> {
                    onShareCounterByToolbar()
                }
            }
            return false
        }

        /** */
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = mode?.menuInflater
            inflater?.inflate(R.menu.action_mode_menu, menu)
            return true
        }

        /** */
        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            menu?.findItem(R.id.action_delete)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            return true
        }

        /** */
        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            clearSelectedCounters()
        }
    }

    /** */
    private fun onShareCounterByToolbar() {
        val listCounterSelection = countersAdapter.currentList
            .filter { it.isSelected }
            .map { it.toCounter() }

        val counter = listCounterSelection.first()
        val sharedCodeIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, counter.title)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sharedCodeIntent, null)
        startActivity(shareIntent)
    }


    /** */
    private fun deleteCounterByToolbar() {
        val listCounterSelection = countersAdapter.currentList
            .filter { it.isSelected }
            .map { it.toCounter() }
        if (listCounterSelection.size > 1)
            showSnackBar(R.string.delete_counter_message_error)
        else {
            val counter = listCounterSelection.first()
            deleteCounterDialogAlert(counter.title, counter.id)
        }

    }

    /** */
    private fun clearSelectedCounters() {
        countersAdapter.currentList
            .forEach {
                it.isSelected = false
            }
        countersAdapter.notifyDataSetChanged()
    }

    /** */
    private fun deleteCounterDialogAlert(name: String, id: String) {
        val title: String = getString(
            R.string.delete_x_question,
            name
        )

        val action = ::positiveDeleteDialogDialog
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_Dialog)
            .setTitle(title)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                action.invoke(id)
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    /** */
    private fun positiveDeleteDialogDialog(id: String) {
        actionMode?.finish()
        executeDeleteCounter(id)
    }

    /** */
    private fun modifierCounterDialogAlert(counter: Counter) {
        val title: String = getString(
            R.string.error_updating_counter_title,
            counter.title, counter.count + 1
        )
        val message = R.string.connection_error_description
        val action = ::retryIncrementCounterDialog
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_Dialog)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(R.string.retry) { dialog, _ ->
                action.invoke(counter.id)
                dialog.dismiss()
            }
            .setPositiveButton(R.string.dismiss) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    /** */
    private fun retryIncrementCounterDialog(id: String) {
        actionMode?.finish()
        executeIncreaseCounter(id)
    }

}