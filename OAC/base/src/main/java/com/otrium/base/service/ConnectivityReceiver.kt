package com.otrium.base.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.otrium.base.service.BaseRequest.Companion.context

class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        val cm = p0?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnected
        if (connectivityReceiver != null) {
            connectivityReceiver?.onNetworkChange(isConnected)
        }
    }

    interface ConnectivityReceiverListener {
        fun onNetworkChange(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiver: ConnectivityReceiverListener? = null

        val isConnected: Boolean
            get() {
                val cm =
                    context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnected
            }

    }

}