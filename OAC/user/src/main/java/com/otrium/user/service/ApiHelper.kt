package com.otrium.user.service

import com.apollographql.apollo.ApolloQueryCall
import com.otrium.user.UserDetailsQuery
import io.reactivex.Observable

interface ApiHelper {

    /**
     * Get user details request
     *
     * @param userDetailsRequestQuery   User details query
     * @return User details
     */
    fun getUserProfileDetails(userDetailsRequestQuery: ApolloQueryCall<UserDetailsQuery.Data>): Observable<UserDetailsQuery.Data>

}