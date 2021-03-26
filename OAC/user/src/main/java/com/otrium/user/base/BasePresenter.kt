package com.otrium.user.base

import android.content.Context
import com.otrium.user.service.DataManager
import javax.inject.Inject

open class BasePresenter<V : MvpView>@Inject
constructor(
    private val dataManager: DataManager,
    var context: Context
) : MvpPresenter<V> {

    private var mMvpView: V? = null

    override fun onAttach(mvpView: V?) {

        mMvpView = mvpView

    }

    override fun onDetach() {

        mMvpView = null

    }

    fun isViewAttached(): Boolean {

        return mMvpView != null

    }

    fun getMvpView(): V? {

        return mMvpView

    }

    fun getDataManager(): DataManager {

        return dataManager

    }

}