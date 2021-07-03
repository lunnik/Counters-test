package com.example.counters

import com.example.counters.data.CountersDataSource
import com.example.counters.data.CountersRepositoryImpl
import com.example.counters.data.data_source.dummy.CountersDataSourceDummy
import com.example.counters.data.data_source.local.CountersDataSourceLocal
import com.example.counters.data.data_source.remote.CountersApiService
import com.example.counters.data.data_source.remote.CountersDataSourceRemote
import com.example.counters.domain.CountersRepository
import com.example.counters.domain.use_case.add_counter.AddCounterUseCase
import com.example.counters.domain.use_case.decrease_counter.DecreaseCounterUseCase
import com.example.counters.domain.use_case.delete_counter.DeleteCounterUseCase
import com.example.counters.domain.use_case.get_counters.GetCountersUseCase
import com.example.counters.domain.use_case.increase_counter.IncreaseCounterUseCase
import com.example.counters.presentation.add_counter.AddCounter
import com.example.counters.presentation.add_counter.AddCounterImpl
import com.example.counters.presentation.decrease_counter.DecreaseCounter
import com.example.counters.presentation.decrease_counter.DecreaseCounterImpl
import com.example.counters.presentation.delete_counter.DeleteCounter
import com.example.counters.presentation.delete_counter.DeleteCounterImpl
import com.example.counters.presentation.get_counters.GetCounters
import com.example.counters.presentation.get_counters.GetCountersImpl
import com.example.counters.presentation.increase_counter.IncreaseCounter
import com.example.counters.presentation.increase_counter.IncreaseCounterImpl
import org.koin.dsl.module.module
import retrofit2.Retrofit

val countersModule = module {

    /** PRESENTATION **/
    single<GetCounters> { GetCountersImpl(getCountersUseCase = get()) }
    single<IncreaseCounter> { IncreaseCounterImpl(increaseCounterUseCase = get()) }
    single<DecreaseCounter> { DecreaseCounterImpl(decreaseCounterUseCase = get()) }
    single<DeleteCounter> { DeleteCounterImpl(deleteCounterUseCase = get()) }
    single<AddCounter> { AddCounterImpl(addCounterUseCase = get()) }

    /** USE CASE **/
    factory { GetCountersUseCase(countersRepository = get()) }
    factory { IncreaseCounterUseCase(countersRepository = get()) }
    factory { DecreaseCounterUseCase(countersRepository = get()) }
    factory { DeleteCounterUseCase(countersRepository = get()) }
    factory { AddCounterUseCase(countersRepository = get()) }

    /** REPOSITORY **/
    single<CountersRepository> {
        CountersRepositoryImpl(
            countersDataSourceRemote = get(),
            counterDataSourceLocal = get(),
            countersDataSourceDummy = get(),
            internetConnectionRepository = get()
        )
    }

    /** DATA SOURCE **/
    single<CountersDataSource> {
        CountersDataSourceRemote(countersApiService = get(), counterDao = get())
    }
    single { CountersDataSourceDummy() }
    single { CountersDataSourceRemote(countersApiService = get(), counterDao = get()) }
    single { CountersDataSourceLocal(counterDao = get()) }

    /** API SERVICE **/
    single<CountersApiService> {
        get<Retrofit>().create(CountersApiService::class.java)
    }


}