package com.otrium.user.feature.profile

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otrium.base.helpers.ViewTaskHandler
import com.otrium.base.helpers.runOnUI
import com.otrium.user.R
import com.otrium.user.UserDetailsQuery
import com.otrium.user.base.BaseFragment
import com.otrium.user.databinding.FragmentUserProfileBinding
import com.otrium.user.di.component.UserComponent
import com.otrium.user.feature.profile.adapter.UserProfilePinnedListAdapter
import com.otrium.user.feature.profile.adapter.UserProfileStarredListAdapter
import com.otrium.user.feature.profile.adapter.UserProfileTopListAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>(), UserProfileMvpView {

    private var fragmentUserProfileBinding: FragmentUserProfileBinding? = null

    override val layoutId: Int
        get() = R.layout.fragment_user_profile


    @Inject
    lateinit var userProfilePresenter: UserProfileMvpPresenter<UserProfileMvpView>

    @Inject
    lateinit var userProfilePinnedListAdapter: UserProfilePinnedListAdapter

    @Inject
    lateinit var userProfileTopListAdapter: UserProfileTopListAdapter

    @Inject
    lateinit var userProfileStarredListAdapter: UserProfileStarredListAdapter

    private var gitUsername = "sindresorhus"

    override fun initialize() {

        fragmentUserProfileBinding = getViewDataBinding()

        val component: UserComponent? = getActivityComponent()
        component?.inject(this)
        userProfilePresenter.onAttach(this)

    }

    override fun defineLayout() {

        fragmentUserProfileBinding?.userPinnedListRecyclerView?.adapter =
            userProfilePinnedListAdapter
        fragmentUserProfileBinding?.userPinnedListRecyclerView?.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

        fragmentUserProfileBinding?.userTopListRecyclerView?.adapter =
            userProfileTopListAdapter
        fragmentUserProfileBinding?.userTopListRecyclerView?.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

        fragmentUserProfileBinding?.userStarredListRecyclerView?.adapter =
            userProfileStarredListAdapter
        fragmentUserProfileBinding?.userStarredListRecyclerView?.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

    }

    override fun getData() {

        getUserDetails()

    }

    override fun setData() {

    }

    override fun setListeners() {

        fragmentUserProfileBinding?.userPinnedListBtn?.setListener {
            userProfilePresenter.navigateToPinnedRepositories()
        }

        fragmentUserProfileBinding?.userTopListBtn?.setListener {
            userProfilePresenter.navigateToTopRepositories()
        }

        fragmentUserProfileBinding?.userStarredListBtn?.setListener {
            userProfilePresenter.navigateToStarredRepositories()
        }

    }

    private fun getUserDetails() {

        userProfilePresenter.getUserProfileDetails(gitUsername)

    }

    override fun setUserDetails(userDetails: UserDetailsQuery.User) {

        ViewTaskHandler.runOnUI {
            Picasso.get()
                .load(userDetails.avatarUrl.toString())
                .placeholder(R.drawable.ic_otrium_logo)
                .into(fragmentUserProfileBinding?.userProfileImageImg)
            fragmentUserProfileBinding?.userNameTv?.setTextViewText(userDetails.name)
            fragmentUserProfileBinding?.userUsernameTv?.setTextViewText(userDetails.login)
            fragmentUserProfileBinding?.userEmailTv?.setTextViewText(userDetails.email)
            fragmentUserProfileBinding?.userFollowersCountTv?.setTextViewText(userDetails.followers.totalCount.toString())
            fragmentUserProfileBinding?.userFollowingCountTv?.setTextViewText(userDetails.following.totalCount.toString())
            userDetails.pinnedItems.nodes?.let { pinnedList ->
                userProfilePinnedListAdapter.addItems(pinnedList)
            }
            userDetails.topRepositories.nodes?.let { userProfileTopListAdapter.addItems(it) }
            userDetails.starredRepositories.nodes?.let {
                userProfileStarredListAdapter.addItems(
                    it.take(
                        10
                    )
                )
            }
        }

    }

}