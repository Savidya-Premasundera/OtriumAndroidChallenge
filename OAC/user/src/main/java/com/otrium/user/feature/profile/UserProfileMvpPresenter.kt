package com.otrium.user.feature.profile

import com.otrium.user.base.MvpPresenter

interface UserProfileMvpPresenter<V : UserProfileMvpView> : MvpPresenter<V> {

    fun getUserProfileDetails(username: String)

    fun navigateToPinnedRepositories()

    fun navigateToTopRepositories()

    fun navigateToStarredRepositories()

}
