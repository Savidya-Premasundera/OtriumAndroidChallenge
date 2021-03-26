package com.otrium.user.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.otrium.user.di.scope.UserScope
import com.otrium.user.feature.activity.user.UserActivityMvpPresenter
import com.otrium.user.feature.activity.user.UserActivityMvpView
import com.otrium.user.feature.activity.user.UserActivityPresenter
import com.otrium.user.feature.profile.UserProfileMvpPresenter
import com.otrium.user.feature.profile.UserProfileMvpView
import com.otrium.user.feature.profile.UserProfilePresenter
import com.otrium.user.feature.profile.adapter.UserProfilePinnedListAdapter
import com.otrium.user.feature.profile.adapter.UserProfileStarredListAdapter
import com.otrium.user.feature.profile.adapter.UserProfileTopListAdapter
import com.otrium.user.service.*
import com.otrium.user.service.impl.ApiClientImpl
import com.otrium.user.service.impl.ApiImpl
import com.otrium.user.service.impl.DataManagerImpl
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class UserModule(activity: AppCompatActivity) {

    private var mActivity: AppCompatActivity = activity

    @Provides
    fun provideContext(): Context {

        return mActivity

    }

    @Provides
    fun provideActivity(): AppCompatActivity {

        return mActivity

    }

    @Provides
    fun provideApiClient(apiClientManager: ApiClientImpl): ApiClientHelper {

        return apiClientManager

    }

    @Provides
    fun provideApiService(apiManager: ApiImpl): ApiHelper {

        return apiManager

    }


    @Provides
    fun provideDataManager(appDataManager: DataManagerImpl): DataManager {

        return appDataManager

    }

    @Provides
    @UserScope
    fun provideUserActivityPresenter(
        presenter: UserActivityPresenter<UserActivityMvpView>
    ): UserActivityMvpPresenter<UserActivityMvpView> {

        return presenter

    }

    @Provides
    fun provideUserProfilePresenter(
        presenter: UserProfilePresenter<UserProfileMvpView>
    ): UserProfileMvpPresenter<UserProfileMvpView> {

        return presenter

    }

    @Provides
    fun providePinnedListAdapter(): UserProfilePinnedListAdapter {

        return UserProfilePinnedListAdapter(ArrayList(), mActivity)

    }

    @Provides
    fun provideTopListAdapter(): UserProfileTopListAdapter {

        return UserProfileTopListAdapter(ArrayList(), mActivity)

    }

    @Provides
    fun provideStarredListAdapter(): UserProfileStarredListAdapter {

        return UserProfileStarredListAdapter(ArrayList(), mActivity)

    }

}