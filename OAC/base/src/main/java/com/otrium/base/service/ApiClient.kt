package com.otrium.base.service

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.cache.http.ApolloHttpCache
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore
import com.otrium.base.BuildConfig
import okhttp3.OkHttpClient
import java.io.File

object ApiClient {

    /**
     * Create apollo client
     *
     * @return Apollo client
     */
    fun createClient(context: Context): ApolloClient {

        return ApolloClient.builder()
            .serverUrl(ServiceURL.BASE_URL)
            .httpCache(ApolloHttpCache(createCacheStore(context)))
            .defaultHttpCachePolicy(HttpCachePolicy.CACHE_FIRST)
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
                    "Bearer " + BuildConfig.TOKEN.replace("@TOKEN@", "")
                )
                it.proceed(builder.build())
            }
            .build()

    }

    /**
     * Provide cache store
     *
     * @param context   Context
     */
    private fun createCacheStore(context: Context): DiskLruHttpCacheStore{

        val file = File(context.cacheDir, "apolloCache")
        val size: Long = 1024 * 1024
        return DiskLruHttpCacheStore(file, size)

    }

}