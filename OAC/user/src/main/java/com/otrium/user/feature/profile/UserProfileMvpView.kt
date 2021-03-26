package com.otrium.user.feature.profile

import com.otrium.user.UserDetailsQuery
import com.otrium.user.base.MvpView

interface UserProfileMvpView : MvpView {

    fun setUserDetails(userDetails: UserDetailsQuery.User)

}
