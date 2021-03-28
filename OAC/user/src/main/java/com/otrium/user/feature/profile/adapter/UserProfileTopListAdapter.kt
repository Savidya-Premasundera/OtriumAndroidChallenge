package com.otrium.user.feature.profile.adapter

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otrium.base.enums.ListViewType
import com.otrium.user.UserDetailsQuery
import com.otrium.user.base.BaseViewHolder
import com.otrium.user.databinding.ListviewTopRepoBinding
import com.otrium.user.databinding.ListviewTopRepoEmptyBinding

class UserProfileTopListAdapter(
    private val userTopRepoList: MutableList<UserDetailsQuery.Node1?>?,
    private val hostActivity: Activity?
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.onBind(position)

    }

    override fun getItemViewType(position: Int): Int {

        return if (userTopRepoList != null && userTopRepoList.size > 0) {
            ListViewType.VIEW_TYPE_NORMAL.viewType
        } else {
            ListViewType.VIEW_TYPE_EMPTY.viewType
        }

    }

    override fun getItemCount(): Int {

        return if (userTopRepoList != null && userTopRepoList.size > 0) {
            userTopRepoList.size
        } else {
            1
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            ListViewType.VIEW_TYPE_NORMAL.viewType -> {
                val topViewBinding = ListviewTopRepoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return TopViewHolder(topViewBinding)
            }
            ListViewType.VIEW_TYPE_EMPTY.viewType -> {
                val emptyViewBinding = ListviewTopRepoEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return TopEmptyViewHolder(emptyViewBinding)
            }
            else -> {
                val emptyViewBinding = ListviewTopRepoEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return TopEmptyViewHolder(emptyViewBinding)
            }
        }

    }

    fun addItems(topRepoList: List<UserDetailsQuery.Node1?>) {

        userTopRepoList?.addAll(topRepoList)
        notifyDataSetChanged()

    }

    inner class TopViewHolder(private val topListBinding: ListviewTopRepoBinding) :
        BaseViewHolder(topListBinding.root) {

        override fun onBind(position: Int) {

            val topRepo = userTopRepoList!![position]
            hostActivity?.applicationContext?.let { context ->
                topListBinding.repoTopItemCont.setIconDrawable(
                    context,
                    topRepo?.owner?.avatarUrl.toString()
                )
            }
            topListBinding.repoTopItemCont.setTopTextViewText(topRepo?.owner?.login)
            topListBinding.repoTopItemCont.setDescription01TextViewText(topRepo?.name)
            topListBinding.repoTopItemCont.setDescription02TextViewText(topRepo?.description)
            if (topRepo?.stargazers?.totalCount.toString().isEmpty()) {
                topListBinding.repoTopItemCont.setEmptyStar()
            } else {
                topListBinding.repoTopItemCont.setStarTextViewText(topRepo?.stargazers?.totalCount.toString())
            }
            if (topRepo?.primaryLanguage?.name.isNullOrEmpty()) {
                topListBinding.repoTopItemCont.setEmptyTag()
            } else {
                topListBinding.repoTopItemCont.setTagColor(Color.parseColor(topRepo?.primaryLanguage?.color))
                topListBinding.repoTopItemCont.setTagTextViewText(topRepo?.primaryLanguage?.name)
            }

        }

    }

    inner class TopEmptyViewHolder(topEmptyListBinding: ListviewTopRepoEmptyBinding) :
        BaseViewHolder(topEmptyListBinding.root) {

        override fun onBind(position: Int) {

        }

    }

}
