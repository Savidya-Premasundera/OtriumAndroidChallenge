package com.otrium.user.feature.profile.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otrium.base.enums.ListViewType
import com.otrium.user.UserDetailsQuery
import com.otrium.user.base.BaseViewHolder
import com.otrium.user.databinding.ListviewPinnedRepoBinding
import com.otrium.user.databinding.ListviewPinnedRepoEmptyBinding

class UserProfilePinnedListAdapter(
    private val userPinnedRepoList: MutableList<UserDetailsQuery.Node?>?,
    private val hostActivity: Activity?
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.onBind(position)

    }

    override fun getItemViewType(position: Int): Int {

        return if (userPinnedRepoList != null && userPinnedRepoList.size > 0) {
            ListViewType.VIEW_TYPE_NORMAL.viewType
        } else {
            ListViewType.VIEW_TYPE_EMPTY.viewType
        }

    }

    override fun getItemCount(): Int {

        return if (userPinnedRepoList != null && userPinnedRepoList.size > 0) {
            userPinnedRepoList.size
        } else {
            1
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            ListViewType.VIEW_TYPE_NORMAL.viewType -> {
                val pinnedViewBinding = ListviewPinnedRepoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return PinnedViewHolder(pinnedViewBinding)
            }
            ListViewType.VIEW_TYPE_EMPTY.viewType -> {
                val emptyViewBinding = ListviewPinnedRepoEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return PinnedEmptyViewHolder(emptyViewBinding)
            }
            else -> {
                val emptyViewBinding = ListviewPinnedRepoEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return PinnedEmptyViewHolder(emptyViewBinding)
            }
        }

    }

    fun addItems(pinnedRepoList: List<UserDetailsQuery.Node?>) {

        userPinnedRepoList?.addAll(pinnedRepoList)
        notifyDataSetChanged()

    }

    inner class PinnedViewHolder(private val pinnedListBinding: ListviewPinnedRepoBinding) :
        BaseViewHolder(pinnedListBinding.root) {

        override fun onBind(position: Int) {

            val pinnedRepo = userPinnedRepoList!![position]?.asRepository
            hostActivity?.applicationContext?.let { context ->
                pinnedListBinding.repoPinnedItemCont.setIconDrawable(
                    context,
                    pinnedRepo?.owner?.avatarUrl.toString()
                )
            }
            pinnedListBinding.repoPinnedItemCont.setTopTextViewText(pinnedRepo?.owner?.login)
            pinnedListBinding.repoPinnedItemCont.setDescription01TextViewText(pinnedRepo?.name)
            pinnedListBinding.repoPinnedItemCont.setDescription02TextViewText(pinnedRepo?.description)
            if (pinnedRepo?.stargazers?.totalCount.toString().isEmpty()) {
                pinnedListBinding.repoPinnedItemCont.setEmptyStar()
            } else {
                pinnedListBinding.repoPinnedItemCont.setStarTextViewText(pinnedRepo?.stargazers?.totalCount.toString())
            }
            if (pinnedRepo?.primaryLanguage?.name.isNullOrEmpty()) {
                pinnedListBinding.repoPinnedItemCont.setEmptyTag()
            } else {
                pinnedListBinding.repoPinnedItemCont.setTagTextViewText(pinnedRepo?.primaryLanguage?.name)
            }
        }

    }

    inner class PinnedEmptyViewHolder(pinnedEmptyListBinding: ListviewPinnedRepoEmptyBinding) :
        BaseViewHolder(pinnedEmptyListBinding.root) {

        override fun onBind(position: Int) {

        }

    }

}
