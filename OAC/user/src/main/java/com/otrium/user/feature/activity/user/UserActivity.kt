package com.otrium.user.feature.activity.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import com.otrium.base.helpers.NavigationHelper.pushFragment
import com.otrium.base.util.ScreenUtil
import com.otrium.user.R
import com.otrium.user.base.BaseActivity
import com.otrium.user.databinding.ActivityUserBinding
import com.otrium.user.enum.FragmentId
import com.otrium.user.feature.profile.UserProfileFragment

class UserActivity : BaseActivity<ActivityUserBinding>(), UserActivityMvpView {

    override val layoutId: Int
        get() = R.layout.activity_user

    private var activityUserBinding: ActivityUserBinding? = null
    private lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        try {
            initialize()
        } catch (nullException: NullPointerException) {
            val intent = Intent(this, UserActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }


        defineLayout()

        getData()

        setData()

        setListeners()

    }

    override fun initialize() {

        mActivity = this

        val displayMetrics = DisplayMetrics()
        mActivity.display?.getRealMetrics(displayMetrics)
        ScreenUtil.height = displayMetrics.heightPixels
        ScreenUtil.width = displayMetrics.widthPixels

    }

    override fun defineLayout() {

    }


    override fun getData() {

    }


    override fun setData() {

        activityUserBinding = getViewDataBinding()
        getActivityComponent()?.inject(this)

        pushFragment(supportFragmentManager, UserProfileFragment(), FragmentId.USER_PROFILE.name)

    }

    override fun setListeners() {

    }

}