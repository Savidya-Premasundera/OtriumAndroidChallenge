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
    private val context: Context,
    private val apiClientHelper: ApiClientHelper,
    private val apiHelper: ApiHelper
) : DataManager {

    override fun getApiClient(context: Context): ApolloClient {

        return apiClientHelper.getApiClient(context)

    }

    override fun getUserProfileDetails(userDetailsRequestQuery: ApolloQueryCall<UserDetailsQuery.Data>): Observable<UserDetailsQuery.Data> {

        return apiHelper.getUserProfileDetails(userDetailsRequestQuery)

    }

}