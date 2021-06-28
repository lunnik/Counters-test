package com.example.network.internet_connection

import androidx.lifecycle.LiveData

/** */
interface InternetConnectionRepository {

    /* */
    val isOnline: Boolean

    /* */
    val isOnlineLiveData: LiveData<Boolean>

    /** */
    suspend fun fetch()

}
