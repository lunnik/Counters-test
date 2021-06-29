package com.cornershop.counterstest.di.presentation

import com.cornershop.counterstest.presentation.home.HomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


/* */
val homeModule: Module = module {

    /* */
    viewModel {
        HomeViewModel(getCounters = get())
    }

}