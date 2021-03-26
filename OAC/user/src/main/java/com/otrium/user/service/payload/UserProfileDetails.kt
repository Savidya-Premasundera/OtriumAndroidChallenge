package com.otrium.user.service.payload

import com.apollographql.apollo.ApolloQueryCall
import com.otrium.base.service.BaseRequest
import com.otrium.user.UserDetailsQuery
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class UserProfileDetails {

    /**
     * Get user details
     *
     * @param userDetailsRequestQuery   User details query
     * @return User details
     */
    fun getUserDetails(
        userDetailsRequestQuery: ApolloQueryCall<UserDetailsQuery.Data>,
    ): Observable<UserDetailsQuery.Data> {

        return PublishSubject.create { observable ->
            BaseRequest().executeRequest(
                requestQuery = userDetailsRequestQuery,
                onResult = {
                    observable.onNext(it)
                    observable.onComplete()
                },
                onError = {
                    observable.onError(it)
                })
        }

    }

}