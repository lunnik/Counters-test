package com.example.network.internet_connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.standalone.KoinComponent


/**
 *
 */
class InternetConnectionRepositoryImpl() : InternetConnectionRepository, KoinComponent {

    /* */
    private var _isOnline: Boolean = false
    override val isOnline: Boolean get() = _isOnline

    /* */
    private var _isOnlineLiveData = MutableLiveData<Boolean>()
    override val isOnlineLiveData: LiveData<Boolean> get() = _isOnlineLiveData

    /** */
    override suspend fun fetch() {
        _isOnline = InternetConnectionState.haveNetworkConnection()

    }

}
