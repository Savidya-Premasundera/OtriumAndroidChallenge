package com.otrium.base.containers

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.otrium.base.R
import com.otrium.base.databinding.ContainerRepositoryBinding
import com.squareup.picasso.Picasso

class RepositoryItemContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(
    context, attrs, defStyleAttr
) {

    private lateinit var containerRepositoryBinding: ContainerRepositoryBinding

    init {

        containerRepositoryBinding =
            ContainerRepositoryBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.RepositoryItemContainer, 0, 0
            )

            if (typedArray.hasValue(R.styleable.RepositoryItemContainer_repo_icon)) {
                val textViewImage = typedArray.getDrawable(
                    R.styleable.RepositoryItemContainer_repo_icon
                )
                setIconDrawable(textViewImage)
            }

            if (typedArray.hasValue(R.styleable.RepositoryItemContainer_repo_top_text)) {
                val textViewText = typedArray.getString(
                    R.styleable.RepositoryItemContainer_repo_top_text
                )
                setTopTextViewText(textViewText)
            }

            if (typedArray.hasValue(R.styleable.RepositoryItemContainer_repo_description_01_text)) {
                val textViewText = typedArray.getString(
                    R.styleable.RepositoryItemContainer_repo_description_01_text
                )
                setDescription01TextViewText(textViewText)
            }

            if (typedArray.hasValue(R.styleable.RepositoryItemContainer_repo_description_02_text)) {
                val textViewText = typedArray.getString(
                    R.styleable.RepositoryItemContainer_repo_description_02_text
                )
                setDescription02TextViewText(textViewText)
            }

            if (typedArray.hasValue(R.styleable.RepositoryItemContainer_repo_star_text)) {
                val textViewText = typedArray.getString(
                    R.styleable.RepositoryItemContainer_repo_star_text
                )
                setStarTextViewText(textViewText)
            }

            if (typedArray.hasValue(R.styleable.RepositoryItemContainer_repo_tag_text)) {
                val textViewText = typedArray.getString(
                    R.styleable.RepositoryItemContainer_repo_tag_text
                )
                setTagTextViewText(textViewText)
            }

        }

    }

    /**
     * Set drawable
     *
     * @param drawable Drawable
     */
    fun setIconDrawable(drawable: Drawable?) {

        containerRepositoryBinding.repoIconImg.setImageDrawable(drawable)

    }

    /**
     * Set drawable
     *
     * @param context       Context
     * @param uri           Image URL
     * @param placeholder   Default icon
     */
    fun setIconDrawable(
        context: Context,
        uri: String?,
        placeholder: Drawable? = ContextCompat.getDrawable(
            context,
            R.drawable.ic_otrium_logo
        )
    ) {

        Picasso.with(context)
            .load(uri)
            .placeholder(placeholder)
            .into(containerRepositoryBinding.repoIconImg)

    }

    /**
     * Set top text
     *
     * @param text Text view text
     */
    fun setTopTextViewText(text: String?) {

        containerRepositoryBinding.repoTopTextTv.setTextViewText(text)

    }

    /**
     * Set description 01 text
     *
     * @param text Text view text
     */
    fun setDescription01TextViewText(text: String?) {

        containerRepositoryBinding.repoDescription01Tv.setTextViewText(text)

    }

    /**
     * Set description 02 text
     *
     * @param text Text view text
     */
    fun setDescription02TextViewText(text: String?) {

        containerRepositoryBinding.repoDescription02Tv.setTextViewText(text)

    }

    /**
     * Set star text
     *
     * @param text Text view text
     */
    fun setStarTextViewText(text: String?) {

        containerRepositoryBinding.repoStarTv.setTextViewText(text)

    }

    /**
     * Set tag text
     *
     * @param text Text view text
     */
    fun setTagTextViewText(text: String?) {

        containerRepositoryBinding.repoTagTv.setTextViewText(text)

    }

    /**
     * Set empty star
     */
    fun setEmptyStar() {

        containerRepositoryBinding.repoStarImg.visibility= View.GONE
        containerRepositoryBinding.repoStarImg.visibility= View.GONE

    }

    /**
     * Set empty tag
     */
    fun setEmptyTag() {

        containerRepositoryBinding.repoTagImg.visibility= View.GONE
        containerRepositoryBinding.repoTagTv.visibility= View.GONE

    }

}