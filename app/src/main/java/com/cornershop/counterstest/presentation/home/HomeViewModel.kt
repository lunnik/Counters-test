package com.cornershop.counterstest.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterParams
import com.example.counters.domain.use_case.delete_counter.DeleteCounterParams
import com.example.counters.domain.use_case.get_counters.GetCountersParams
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterParams
import com.example.counters.presentation.decrease_counter.DecreaseCounter
import com.example.counters.presentation.decrease_counter.DecreaseCountersStatus
import com.example.counters.presentation.delete_counter.DeleteCounter
import com.example.counters.presentation.delete_counter.DeleteCountersStatus
import com.example.counters.presentation.get_counters.GetCounters
import com.example.counters.presentation.get_counters.GetCountersStatus
import com.example.counters.presentation.increase_counter.IncreaseCounter
import com.example.counters.presentation.increase_counter.IncreaseCountersStatus

class HomeViewModel(
    getCounters: GetCounters,
    increaseCounter: IncreaseCounter,
    decreaseCounter: DecreaseCounter,
    deleteCounter: DeleteCounter
) : ViewModel(),
    GetCounters by getCounters,
    IncreaseCounter by increaseCounter,
    DecreaseCounter by decreaseCounter,
    DeleteCounter by deleteCounter {

    /** */
    fun getCountersAsLiveData():
            LiveData<GetCountersStatus> {
        return getCountersAsLiveData(GetCountersParams)
    }

    /** */
    fun increaseCountersAsLiveData(id: String):
            LiveData<IncreaseCountersStatus> {
        return increaseCountersAsLiveData(IncreaseCounterParams(id))
    }

    /** */
    fun decreaseCountersAsLiveData(id: String):
            LiveData<DecreaseCountersStatus> {
        return decreaseCountersAsLiveData(DecreaseCounterParams(id))
    }

    /** */
    fun deleteCountersAsLiveData(id: String): LiveData<DeleteCountersStatus> {
        val params = DeleteCounterParams(id)
        return deleteCountersAsLiveData(params)
    }


}