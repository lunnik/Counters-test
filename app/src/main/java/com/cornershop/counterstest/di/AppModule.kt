package com.cornershop.counterstest.di

import com.cornershop.counterstest.TestApp
import com.example.data_source.data.httpClientModule
import com.example.network.networkModule
import com.cornershop.counterstest.di.data_source.shared_module.serverUrlProvider
import com.cornershop.counterstest.di.presentation.homeModule
import com.example.counters.countersModule
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module

/**
 * Initialize the Koin instance with the modules associated to the project.
 */
fun TestApp.initKoin() {
    val sharedModules = getSharedModules()
    val featureModules = getFeatureModules()
    val presentationModules = getPresentationModules()
    val modules = sharedModules + featureModules + presentationModules
    startKoin(applicationContext, modules)
}

/**
 *
 */
private fun getSharedModules(): List<Module> = listOf(
    /** NETWORK **/
    networkModule,
    /** DATA SOURCE **/
    httpClientModule,
    serverUrlProvider,
)

/** */
private fun getFeatureModules(): List<Module> = listOf(
    countersModule

)

/**
 *
 */
private fun getPresentationModules(): List<Module> = listOf(
    homeModule
)
