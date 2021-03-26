package com.otrium.user.service.impl

import com.apollographql.apollo.ApolloQueryCall
import com.otrium.user.UserDetailsQuery
import com.otrium.user.service.ApiHelper
import com.otrium.user.service.payload.UserProfileDetails
import io.reactivex.Observable
import javax.inject.Inject

class ApiImpl @Inject constructor() : ApiHelper {

    override fun getUserProfileDetails(userDetailsRequestQuery: ApolloQueryCall<UserDetailsQuery.Data>): Observable<UserDetailsQuery.Data> {

        return UserProfileDetails().getUserDetails(userDetailsRequestQuery)

    }

}