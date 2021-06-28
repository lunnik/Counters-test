package com.example.data_source.data.remote.http_client.retrofit

import com.example.data_source.domain.ServerUrlProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 *
 */
class RetrofitBuilder(
    private val serverUrlProvider: ServerUrlProvider) {

    /* */
    private val bodyInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    /* */
    private val moshiConverterFactory = MoshiConverterFactory.create()

    /* */
    private val timeOut: Long = 100L


    /**
     *
     */
    fun build(): Retrofit =
        Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(serverUrlProvider.apiBaseUrl)
            .addConverterFactory(moshiConverterFactory)
            .build()

    /**
     *
     */
    private fun buildHttpClient(): OkHttpClient =
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