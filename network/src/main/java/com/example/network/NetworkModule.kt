package com.example.network

import com.example.network.internet_connection.InternetConnectionApiService
import com.example.network.internet_connection.InternetConnectionRepository
import com.example.network.internet_connection.InternetConnectionRepositoryImpl
import com.example.network.internet_connection.InternetConnectionRetrofitBuilder
import org.koin.dsl.module.module


/* */
val networkModule = module {

    /* Internet connection repository instance */
    single<InternetConnectionRepository>(createOnStart = true) {
        InternetConnectionRepositoryImpl()
    }
    /* */
    single<InternetConnectionApiService> {
        InternetConnectionRetrofitBuilder()
            .build()
            .create(InternetConnectionApiService::class.java)
    }

}
