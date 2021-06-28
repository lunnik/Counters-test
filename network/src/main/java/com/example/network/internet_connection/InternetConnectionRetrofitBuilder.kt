package com.example.network.internet_connection

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 *
 */
internal class InternetConnectionRetrofitBuilder {

    /* */
    private val baseUrl: String = "https://clients3.google.com/"
    /* */
    private val timeOut: Long = 5L

    /* */
    private val bodyInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    /**
     *
     */
    fun build() : Retrofit =
        Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(baseUrl)
            .build()

    /**
     *
     */
    private fun buildHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(getHeaderInterceptor())
            .apply { addInterceptor(bodyInterceptor) }
            .build()

    /**
     *
     */
    private fun getHeaderInterceptor() = Interceptor {
        val newRequest = it.request().newBuilder()
            .build()
        it.proceed(newRequest)
    }

}
