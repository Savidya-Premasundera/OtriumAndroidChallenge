package com.otrium.user.feature.profile

import android.content.Context
import com.otrium.base.service.ResultCallBackException
import com.otrium.user.R
import com.otrium.user.UserDetailsQuery
import com.otrium.user.base.BasePresenter
import com.otrium.user.service.DataManager
import javax.inject.Inject

class UserProfilePresenter<V : UserProfileMvpView> @Inject constructor(
    dataManager: DataManager,
    context: Context
) : BasePresenter<V>(dataManager, context), UserProfileMvpPresenter<V> {

    /**
     * Get user profile details interactor
     */
    override fun getUserProfileDetails(username: String) {

        getUserDetails(username, onSuccess = {
            it.user?.let { user ->
                getMvpView()?.setUserDetails(userDetails = user)
            }
        }, onFailException = {
            getMvpView()?.showMessage(it.errorMessage)
        }, onFail = {
            getMvpView()?.showMessage(context.getString(R.string.user_error_message_tv))
        })
    }

    /**
     * Get user details
     *
     * @param username  Username
     * @param onSuccess Success response
     * @param onFail    Fail response
     */
    private fun getUserDetails(
        username: String,
        onSuccess: (UserDetailsQuery.Data) -> Unit,
        onFailException: (ResultCallBackException) -> Unit,
        onFail: () -> Unit
    ) {

        getDataManager().getUserProfileDetails(
            getDataManager().getApiClient(context).query(UserDetailsQuery(username))
        )
            .map {
                onSuccess.invoke(it)
            }.onErrorReturn {
                if (it is ResultCallBackException) {
                    onFailException.invoke(it)
                } else {
                    onFail.invoke()
                }
            }.subscribe()

    }

    /**
     * Navigate to pinned repositories
     */
    override fun navigateToPinnedRepositories() {

        getMvpView()?.showMessage(context.getString(R.string.user_pinned_repo_navigation_tv))

    }

    /**
     * Navigate to top repositories
     */
    override fun navigateToTopRepositories() {

        getMvpView()?.showMessage(context.getString(R.string.user_top_repo_navigation_tv))

    }

    /**
     * Navigate to starred repositories
     */
    override fun navigateToStarredRepositories() {

        getMvpView()?.showMessage(context.getString(R.string.user_starred_repo_navigation_tv))

    }

}