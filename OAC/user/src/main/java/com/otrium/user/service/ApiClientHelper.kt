package com.otrium.user.service

import android.content.Context
import com.apollographql.apollo.ApolloClient

interface ApiClientHelper {

    /**
     * Get API client
     *
     * @param context   Context
     * @return Apollo client
     */
    fun getApiClient(context: Context): ApolloClient

}