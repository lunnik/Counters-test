package com.cornershop.counterstest.di.presentation

import com.cornershop.counterstest.presentation.home.HomeViewModel
import com.cornershop.counterstest.presentation.home.add_counter.AddCounterViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


/* */
val homeModule: Module = module {

    /* */
    viewModel {
        HomeViewModel(
            getCounters = get(),
            increaseCounter = get(),
            decreaseCounter = get()
        )
    }

    /* */
    viewModel {
        AddCounterViewModel(addCounter = get())
    }

}