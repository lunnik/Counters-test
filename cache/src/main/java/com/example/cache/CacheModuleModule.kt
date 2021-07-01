package com.example.cache

import com.example.cache.data.CounterDataSource
import com.example.cache.data.CounterDataSourceImpl
import com.example.cache.data.local.CounterDataBase
import com.example.cache.data.local.CounterDataSourceLocal
import com.example.cache.domain.CounterRepository
import com.example.cache.domain.use_case.add_counter.AddCounterUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/* */
val cacheModule : Module = module  {

    /** USE CASE **/
    factory { AddCounterUseCase(counterRepository = get()) }

    /** REPOSITORY **/
    single<CounterRepository> {
        CounterDataSourceImpl(
            athleteActivityDataSource = get()
        )
    }

    /** DATA SOURCE **/
    single<CounterDataSource> { CounterDataSourceLocal(counterDao = get()) }

    /** ROOM **/
    single { CounterDataBase.getInstance(get()) }
    /**DAO**/
    single { get<CounterDataBase>().athleteActivityDao() }
}