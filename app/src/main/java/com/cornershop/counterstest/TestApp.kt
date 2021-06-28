package com.cornershop.counterstest

import android.app.Application
import android.content.IntentFilter
import com.cornershop.counterstest.di.initKoin
import com.cornershop.counterstest.system.broadcast_receiver.ConnectivityReceiver

class TestApp: Application() {


    override fun onCreate() {
        super.onCreate()
        initKoin()
        initBroadcastReceiver()
    }

    /** */
    private fun initBroadcastReceiver() {
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityReceiver.connectivityChangeInputFilter)
        )
    }
}