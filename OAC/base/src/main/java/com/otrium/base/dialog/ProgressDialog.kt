package com.otrium.base.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.otrium.base.R
import com.otrium.base.databinding.DialogProgressBinding

class ProgressDialog(context: Context) :
    Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    private lateinit var animationFadeIn: Animation
    var binding: DialogProgressBinding = DialogProgressBinding.inflate(LayoutInflater.from(context))

    init {

        this.setContentView(binding.root)

        setContentView(binding.root)

        this.setCancelable(true)

        initialize()

        getData()

        setData()

        setListeners()

    }

    /**
     * Initialize
     */
    private fun initialize() {

    }

    /**
     * Get data
     */
    private fun getData() {

    }

    /**
     * Set data
     */
    private fun setData() {

        animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_back)

    }

    /**
     * Set listeners
     */
    private fun setListeners() {

    }

    /**
     * Show dialog
     */
    fun showDialog() {

        if (!isShowing) {
            try {
                this.show()
            } catch (ex: Exception) {
//                Log.e("Exception Dialog", "")
            }
        }

    }

    /**
     * Close dialog
     */
    fun closeDialog() {

        try {
            if (isShowing) {
                this.dismiss()
            }
        } catch (ex: IllegalArgumentException) {

        }

    }

    override fun onBackPressed() {

        if (!isShowing) {
            super.onBackPressed()
        }

    }

}