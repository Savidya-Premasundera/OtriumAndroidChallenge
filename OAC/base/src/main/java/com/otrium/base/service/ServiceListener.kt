package com.otrium.base.service

interface ServiceListener{

    fun onServiceStart(isForeground : Boolean)

    fun onServiceStop(isForeground : Boolean)

}