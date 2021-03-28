package com.otrium.base.service

import android.content.Context
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.exception.ApolloException
import com.otrium.base.R

class BaseRequest {

    companion object {
        var context: Context? = null
        var serviceListener: ServiceListener? = null

        fun initializeContext(context: Context, serviceListener: ServiceListener) {
            this.context = context
            this.serviceListener = serviceListener
        }
    }

    fun <T> executeRequest(
        requestQuery: ApolloQueryCall<T>,
        onResult: (T) -> Unit,
        onError: (ResultCallBackException) -> Unit,
        isForegroundCall: Boolean? = true
    ) {

        val responseCallBack = object : ApolloCall.Callback<T>() {
            override fun onFailure(e: ApolloException) {
                serviceListener?.onServiceStop(isForegroundCall!!)
                onError(
                    ResultCallBackException(
                        title = context?.getString(R.string.user_error_title_tv),
                        errorMessage = e.message
                    )
                )
            }

            override fun onResponse(response: Response<T>) {
                serviceListener?.onServiceStop(isForegroundCall!!)
                onResult(response.data as T)
            }
        }

        serviceListener?.onServiceStart(isForegroundCall!!)
        val isConnected = ConnectivityReceiver.isConnected
        if (isConnected) {
            requestQuery.enqueue(responseCallBack)
        } else {
            serviceListener?.onServiceStop(isForegroundCall!!)
            onError(
                ResultCallBackException(
                    title = context?.getString(R.string.user_error_title_tv),
                    errorMessage = context?.getString(R.string.user_error_no_internet_tv)
                )
            )
        }

    }

}

class ResultCallBackException(var title: String?, var errorMessage: String?) : Exception()