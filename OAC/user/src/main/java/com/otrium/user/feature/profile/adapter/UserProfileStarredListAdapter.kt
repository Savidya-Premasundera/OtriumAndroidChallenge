package com.otrium.user.feature.profile.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otrium.base.enums.ListViewType
import com.otrium.user.UserDetailsQuery
import com.otrium.user.base.BaseViewHolder
import com.otrium.user.databinding.ListviewStarredRepoBinding
import com.otrium.user.databinding.ListviewStarredRepoEmptyBinding

class UserProfileStarredListAdapter(
    private val userStarredRepoList: MutableList<UserDetailsQuery.Node2?>?,
    private val hostActivity: Activity?
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.onBind(position)

    }

    override fun getItemViewType(position: Int): Int {

        return if (userStarredRepoList != null && userStarredRepoList.size > 0) {
            ListViewType.VIEW_TYPE_NORMAL.viewType
        } else {
            ListViewType.VIEW_TYPE_EMPTY.viewType
        }

    }

    override fun getItemCount(): Int {

        return if (userStarredRepoList != null && userStarredRepoList.size > 0) {
            userStarredRepoList.size
        } else {
            1
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            ListViewType.VIEW_TYPE_NORMAL.viewType -> {
                val starredViewBinding = ListviewStarredRepoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return StarredViewHolder(starredViewBinding)
            }
            ListViewType.VIEW_TYPE_EMPTY.viewType -> {
                val emptyViewBinding = ListviewStarredRepoEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return StarredEmptyViewHolder(emptyViewBinding)
            }
            else -> {
                val emptyViewBinding = ListviewStarredRepoEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return StarredEmptyViewHolder(emptyViewBinding)
            }
        }

    }

    fun addItems(starredRepoList: List<UserDetailsQuery.Node2?>) {

        userStarredRepoList?.addAll(starredRepoList)
        notifyDataSetChanged()

    }

    inner class StarredViewHolder(private val starredListBinding: ListviewStarredRepoBinding) :
        BaseViewHolder(starredListBinding.root) {

        override fun onBind(position: Int) {

            val starredRepo = userStarredRepoList!![position]
            hostActivity?.applicationContext?.let { context ->
                starredListBinding.repoStarredItemCont.setIconDrawable(
                    context,
                    starredRepo?.owner?.avatarUrl.toString()
                )
            }
            starredListBinding.repoStarredItemCont.setTopTextViewText(starredRepo?.owner?.login)
            starredListBinding.repoStarredItemCont.setDescription01TextViewText(starredRepo?.name)
            starredListBinding.repoStarredItemCont.setDescription02TextViewText(starredRepo?.description)
            if (starredRepo?.stargazers?.totalCount.toString().isEmpty()) {
                starredListBinding.repoStarredItemCont.setEmptyStar()
            } else {
                starredListBinding.repoStarredItemCont.setStarTextViewText(starredRepo?.stargazers?.totalCount.toString())
            }
            if (starredRepo?.primaryLanguage?.name.isNullOrEmpty()) {
                starredListBinding.repoStarredItemCont.setEmptyTag()
            } else {
                starredListBinding.repoStarredItemCont.setTagTextViewText(starredRepo?.primaryLanguage?.name)
            }

        }

    }

    inner class StarredEmptyViewHolder(starredEmptyListBinding: ListviewStarredRepoEmptyBinding) :
        BaseViewHolder(starredEmptyListBinding.root) {

        override fun onBind(position: Int) {

        }

    }

}
