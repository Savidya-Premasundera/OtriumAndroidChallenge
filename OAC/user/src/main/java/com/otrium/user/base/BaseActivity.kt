package com.otrium.user.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.otrium.base.di.util.InjectUtils
import com.otrium.base.dialog.ProgressDialog
import com.otrium.base.helpers.ProgressDialogHelper
import com.otrium.base.service.BaseRequest
import com.otrium.base.service.ServiceListener
import com.otrium.user.di.component.DaggerUserComponent
import com.otrium.user.di.component.UserComponent
import com.otrium.user.di.module.UserModule

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), MvpView,
    BaseFragment.Callback {

    var mActivityComponent: UserComponent? = null
    private lateinit var viewDataBinding: T
    private var progressDialog: ProgressDialog? = null

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        performDependencyInjection()

        initialize()

        performDataBinding()

        initializeContext()

        defineLayout()

        getData()

        setData()

        setListeners()

    }

    fun getActivityComponent(): UserComponent? {

        return mActivityComponent

    }

    /**
     * Dependency injection
     */
    private fun performDependencyInjection() {

        mActivityComponent = DaggerUserComponent
            .builder()
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .userModule(UserModule(this))
            .build()

    }

    /**
     * Data binding with the view
     */
    private fun performDataBinding() {

        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding.executePendingBindings()

    }

    /**
     * To return the view data binding
     * @return View data binding
     */
    fun getViewDataBinding(): T {

        return viewDataBinding

    }

    /**
     * Initialize
     */
    private fun initializeContext(){

        BaseRequest.initializeContext(this, getProgressListener())
        progressDialog = ProgressDialog(this)

    }

    /**
     * Initialize
     */
    abstract fun initialize()

    /**
     * Define layout
     */
    abstract fun defineLayout()

    /**
     * Get data
     */
    abstract fun getData()

    /**
     * Set data
     */
    abstract fun setData()

    /**
     * Set listeners
     */
    abstract fun setListeners()

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}

    override fun showMessage(message: String?) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    override fun showLoading() {

        progressDialog?.let {
            ProgressDialogHelper.showDialog(
                this,
                true,
                it
            )
        }

    }

    override fun hideLoading() {

        progressDialog?.let {
            ProgressDialogHelper.hideDialog(true, it)
        }

    }

    override fun hideKeyboard() {

        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }

    /**
     * Get progress listener
     */
    private fun getProgressListener(): ServiceListener {

        return object : ServiceListener {
            override fun onServiceStart(isForeground: Boolean) {
                if (isForeground) {
                    progressDialog?.let {
                        ProgressDialogHelper.showDialog(
                            applicationContext,
                            isForeground,
                            it
                        )
                    }
                }
            }

            override fun onServiceStop(isForeground: Boolean) {
                if (isForeground) {
                    progressDialog?.let {
                        ProgressDialogHelper.hideDialog(isForeground, it)
                    }
                }
            }
        }

    }

}