
package com.cornershop.counterstest.system.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.network.internet_connection.InternetConnectionRepository
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 *
 */
class ConnectivityReceiver : BroadcastReceiver(), KoinComponent {

    /* */
    private val internetConnectionRepository: InternetConnectionRepository by inject()

    /** */
    override fun onReceive(context: Context?, intent: Intent?) {
        runBlocking {
            internetConnectionRepository.fetch()
        }
    }

    /**
     *
     */
    companion object {
        /* */
        const val connectivityChangeInputFilter: String = "android.net.conn.CONNECTIVITY_CHANGE"
    }

}