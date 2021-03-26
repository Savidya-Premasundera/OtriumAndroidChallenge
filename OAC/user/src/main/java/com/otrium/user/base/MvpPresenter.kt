package com.otrium.user.base

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V?)

    fun onDetach()

}