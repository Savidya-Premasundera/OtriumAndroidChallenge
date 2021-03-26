package com.otrium.user.base

interface MvpView {

    fun showLoading()

    fun hideLoading()

    fun showMessage(message: String?)

    fun hideKeyboard()

}