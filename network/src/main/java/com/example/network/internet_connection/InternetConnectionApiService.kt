package com.example.network.internet_connection

import retrofit2.Response
import retrofit2.http.GET

/** */
interface InternetConnectionApiService {

    /** */
    @GET(URL.GENERATE_204)
    suspend fun generate204(): Response<Unit>

    /** */
    private object URL {
        /* */
        const val GENERATE_204: String = "generate_204"

    }

}
