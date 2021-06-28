package com.cornershop.counterstest.di.data_source.shared_module

import com.cornershop.counterstest.BuildConfig
import com.example.data_source.domain.ServerUrlProvider
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/* */
val serverUrlProvider: Module = module {

    single {
        ServerUrlProvider(BuildConfig.API_BASE_URL)
    }

}