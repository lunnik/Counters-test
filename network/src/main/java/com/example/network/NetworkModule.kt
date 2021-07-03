package com.example.network

import com.example.network.internet_connection.InternetConnectionRepository
import com.example.network.internet_connection.InternetConnectionRepositoryImpl
import org.koin.dsl.module.module


/* */
val networkModule = module {

    /* Internet connection repository instance */
    single<InternetConnectionRepository>(createOnStart = true) {
        InternetConnectionRepositoryImpl()
    }

}
