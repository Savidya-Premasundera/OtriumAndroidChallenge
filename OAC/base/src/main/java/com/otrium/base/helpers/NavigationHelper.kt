package com.otrium.base.helpers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.otrium.base.R

object NavigationHelper {

    /**
     * Open another Activity
     *
     * @param activity              Current Activity
     * @param toActivity            To Activity
     * @param isCloseLastActivity   Need to close the last Activity or not
     */
    fun openActivity(
        activity: Activity,
        toActivity: Class<*>,
        isCloseLastActivity: Boolean = false
    ) {

        val intent = Intent(activity, toActivity)
        if (isCloseLastActivity) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity.finish()
        }
        activity.startActivity(intent)

    }

    /**
     * Open fragment
     *
     * @param fragmentManager   Fragment manager
     * @param fragment          Fragment to be pushed
     * @param tagName           Tag name of the fragment
     * @param bundle            Bundle to be passed
     */
    fun pushFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        tagName: String,
        bundle: Bundle? = null
    ) {

        bundle?.let {
            fragment.arguments = it
        }
        val ft = fragmentManager.beginTransaction()
        ft.add(R.id.container, fragment, tagName)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()

    }

}