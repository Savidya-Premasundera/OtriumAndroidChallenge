package com.otrium.user.di.component

import com.otrium.base.di.component.BaseComponent
import com.otrium.user.di.module.UserModule
import com.otrium.user.di.scope.UserScope
import com.otrium.user.feature.activity.user.UserActivity
import com.otrium.user.feature.profile.UserProfileFragment
import dagger.Component

@UserScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [UserModule::class]
)
interface UserComponent {

    fun inject(activity: UserActivity)

    fun inject(fragment: UserProfileFragment)

}