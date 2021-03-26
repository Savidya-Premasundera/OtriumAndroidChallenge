package com.otrium.base.service

import com.apollographql.apollo.ApolloClient
import com.otrium.base.BuildConfig
import okhttp3.OkHttpClient

object ApiClient {

    /**
     * Create apollo client
     *
     * @return Apollo client
     */
    fun createClient(): ApolloClient {

        return ApolloClient.builder()
            .serverUrl(ServiceURL.BASE_URL)
            .okHttpClient(provideHttpClient())
            .build()

    }

    /**
     * Provide Http interceptor
     *
     * @return OkHttpClient interceptor
     */
    private fun provideHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val builder = original.newBuilder().method(
                    original.method,
                    original.body
                )
                builder.addHeader(
                    "authorization",
                    "Bearer " + BuildConfig.TOKEN
                )
                it.proceed(builder.build())
            }
            .build()

    }

}