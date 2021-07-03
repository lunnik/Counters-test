package com.example.cache

import com.example.cache.data.CounterDataSource
import com.example.cache.data.CounterDataSourceImpl
import com.example.cache.data.local.CounterDataBase
import com.example.cache.data.local.CounterDataSourceLocal
import com.example.cache.domain.CounterRepository
import com.example.cache.domain.use_case.add_counter.AddCounterUseCase
import com.example.cache.domain.use_case.get_counters_by_titile.GetCounterByTitleUseCase
import com.example.cache.presentation.get_counters_by_title.GetCounterByTitle
import com.example.cache.presentation.get_counters_by_title.GetCountersByTitleImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/* */
val cacheModule : Module = module  {

    /**PRESENTATION*/
    single<GetCounterByTitle> { GetCountersByTitleImpl(getCounterByTitleUseCase = get()) }

    /** USE CASE **/
    factory { AddCounterUseCase(counterRepository = get()) }
    factory { GetCounterByTitleUseCase(counterRepository = get()) }

    /** REPOSITORY **/
    single<CounterRepository> {
        CounterDataSourceImpl(
            counterDataSource = get()
        )
    }

    /** DATA SOURCE **/
    single<CounterDataSource> { CounterDataSourceLocal(counterDao = get()) }

    /** ROOM **/
    single { CounterDataBase.getInstance(get()) }
    /**DAO**/
    single { get<CounterDataBase>().athleteActivityDao() }
}