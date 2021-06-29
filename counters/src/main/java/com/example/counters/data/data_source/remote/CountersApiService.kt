package com.example.counters.data.data_source.remote

import com.example.counters.data.data_source.remote.model.DecreaseCounterRequest
import com.example.counters.data.data_source.remote.model.IncreaseCounterRequest
import com.example.counters.data.data_source.remote.model.dto.CounterDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

/* */
internal interface CountersApiService {

    /** */
    @GET(URL.GET_COUNTERS)
    suspend fun getCounters(): Response<List<CounterDto>>

    /** */
    @POST(URL.GET_INC)
    suspend fun increaseCounter(
        @Body request: IncreaseCounterRequest
    ): Response<Unit>

    /** */
    @POST(URL.GET_DEC)
    suspend fun decreaseCounter(
        @Body request: DecreaseCounterRequest
    ): Response<Unit>

    /** */
    @DELETE(URL.GET_DELETE)
    suspend fun deleteCounter(): Response<Unit>

    /** */
    private object URL {

        /* */
        const val GET_COUNTERS: String = "counters"

        /* */
        const val GET_INC: String = "counter/inc"

        /* */
        const val GET_DEC: String = "counter/dec"

        /* */
        const val GET_DELETE: String = "counter"

    }

}
