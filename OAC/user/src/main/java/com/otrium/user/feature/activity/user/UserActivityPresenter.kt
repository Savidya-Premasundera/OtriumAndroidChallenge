package com.otrium.user.feature.activity.user

import android.content.Context
import com.otrium.user.base.BasePresenter
import com.otrium.user.service.DataManager
import javax.inject.Inject

class UserActivityPresenter<V : UserActivityMvpView> @Inject constructor(
    dataManager: DataManager,
    context: Context
) :
    BasePresenter<V>(dataManager, context), UserActivityMvpPresenter<V>