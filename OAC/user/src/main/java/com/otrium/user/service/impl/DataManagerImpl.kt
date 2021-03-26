package com.otrium.user.service.impl

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.otrium.user.UserDetailsQuery
import com.otrium.user.service.ApiClientHelper
import com.otrium.user.service.ApiHelper
import com.otrium.user.service.DataManager
import io.reactivex.Observable
import javax.inject.Inject

class DataManagerImpl @Inject
constructor(
    private val Context: Context,
    private val apiClientHelper: ApiClientHelper,
    private val apiHelper: ApiHelper
) : DataManager {

    override fun getApiClient(): ApolloClient {

        return apiClientHelper.getApiClient()

    }

    override fun getUserProfileDetails(userDetailsRequestQuery: ApolloQueryCall<UserDetailsQuery.Data>): Observable<UserDetailsQuery.Data> {

        return apiHelper.getUserProfileDetails(userDetailsRequestQuery)

    }

}