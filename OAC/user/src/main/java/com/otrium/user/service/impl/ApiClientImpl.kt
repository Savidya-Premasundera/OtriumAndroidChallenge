package com.otrium.user.service.impl

import com.apollographql.apollo.ApolloClient
import com.otrium.base.service.ApiClient
import com.otrium.user.service.ApiClientHelper
import javax.inject.Inject

class ApiClientImpl @Inject constructor() :
    ApiClientHelper {

    override fun getApiClient(): ApolloClient {

        return ApiClient.createClient()

    }

}