package com.cornershop.counterstest.presentation.home.add_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cornershop.counterstest.presentation.common.extension.android.input_valitator.counter.CounterFormatError
import com.cornershop.counterstest.presentation.common.extension.android.lifecycle.valueOrEmpty
import com.example.counters.domain.use_case.add_counter.AddCounterParams
import com.example.counters.presentation.add_counter.AddCounter
import com.example.counters.presentation.add_counter.AddCounterStatus

class AddCounterViewModel(
    addCounter: AddCounter
) : ViewModel(),
    AddCounter by addCounter {
    /* */
    val counterInput: MutableLiveData<String> = MutableLiveData()
    val counter: String get() = counterInput.valueOrEmpty


    /** */
    fun addCountersAsLiveData(): LiveData<AddCounterStatus> {
        val params = AddCounterParams(counter)
        return addCountersAsLiveData(params)
    }

    /** */
    fun isValidCounterInformation(): Boolean =
        CounterFormatError.isValidCounter(counter)

}