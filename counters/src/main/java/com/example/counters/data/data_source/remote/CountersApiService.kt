package com.example.counters.data.data_source.remote

import com.example.counters.data.data_source.remote.model.AddCounterRequest
import com.example.counters.data.data_source.remote.model.DecreaseCounterRequest
import com.example.counters.data.data_source.remote.model.DeleteCounterRequest
import com.example.counters.data.data_source.remote.model.IncreaseCounterRequest
import com.example.counters.data.data_source.remote.model.dto.CounterDto
import retrofit2.Response
import retrofit2.http.*

/* */
internal interface CountersApiService {

    /** */
    @GET(URL.GET_COUNTERS)
    suspend fun getCounters(): Response<List<CounterDto>>

    /** */
    @POST(URL.INC_COUNTER)
    suspend fun increaseCounter(
        @Body request: IncreaseCounterRequest
    ): Response<List<CounterDto>>

    /** */
    @POST(URL.DEC_COUNTER)
    suspend fun decreaseCounter(
        @Body request: DecreaseCounterRequest
    ): Response<List<CounterDto>>

    /** */
    @HTTP(method = "DELETE", path = URL.DELETE_COUNTER, hasBody = true)
    suspend fun deleteCounter(
        @Body request: DeleteCounterRequest
    ): Response<List<CounterDto>>

    /** */
    @POST(URL.ADD_DELETE)
    suspend fun addCounter(
        @Body request: AddCounterRequest
    ): Response<List<CounterDto>>

    /** */
    private object URL {

        /* */
        const val GET_COUNTERS: String = "counters"

        /* */
        const val INC_COUNTER: String = "counter/inc"

        /* */
        const val DEC_COUNTER: String = "counter/dec"

        /* */
        const val DELETE_COUNTER: String = "counter"

        /* */
        const val ADD_DELETE: String = "counter"

    }

}
