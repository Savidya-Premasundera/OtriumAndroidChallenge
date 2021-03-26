package com.otrium.user.service

import com.apollographql.apollo.ApolloClient

interface ApiClientHelper {

    /**
     * Get API client
     *
     * @return Apollo client
     */
    fun getApiClient(): ApolloClient

}