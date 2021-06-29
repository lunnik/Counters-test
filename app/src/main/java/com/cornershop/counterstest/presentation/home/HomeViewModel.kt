package com.cornershop.counterstest.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.counters.domain.use_case.get_counters.GetCountersParams
import com.example.counters.presentation.get_counters.GetCounters
import com.example.counters.presentation.get_counters.GetCountersStatus

class HomeViewModel(
    getCounters: GetCounters
) : ViewModel(),
    GetCounters by getCounters {

    /** */
    fun getCountersAsLiveData(): LiveData<GetCountersStatus> {
        return getCountersAsLiveData(GetCountersParams)
    }
}