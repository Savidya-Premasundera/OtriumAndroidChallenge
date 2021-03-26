package com.otrium.user.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.otrium.base.dialog.ProgressDialog
import com.otrium.base.helpers.ProgressDialogHelper
import com.otrium.base.helpers.ViewTaskHandler
import com.otrium.base.helpers.runOnUI
import com.otrium.base.service.BaseRequest
import com.otrium.base.service.ServiceListener
import com.otrium.user.di.component.UserComponent

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), MvpView {

    private var mActivity: BaseActivity<*>? = null
    private lateinit var viewDataBinding: T
    private var rootView: View? = null

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        rootView = viewDataBinding.root
        rootView?.isClickable = false
        rootView?.isFocusable = false
        return rootView

    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()

        initialize()

        defineLayout()

        getData()

        setData()

        setListeners()

    }

    /**
     * To return the view data binding
     * @return View data binding
     */
    fun getViewDataBinding(): T {

        rootView?.isClickable = true
        rootView?.isFocusable = true
        return viewDataBinding

    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity: BaseActivity<*> = context
            this.mActivity = activity
            activity.onFragmentAttached()
        }

    }

    override fun onDetach() {

        mActivity = null
        super.onDetach()

    }

    fun getActivityComponent(): UserComponent? {

        return mActivity?.getActivityComponent()

    }

    fun getBaseActivity(): BaseActivity<*>? {

        return mActivity

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

    interface Callback {

        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }

    override fun showMessage(message: String?) {

        ViewTaskHandler.runOnUI {
            Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun showLoading() {

        mActivity?.showLoading()

    }

    override fun hideLoading() {

        mActivity?.hideLoading()

    }

    override fun hideKeyboard() {

        mActivity?.hideKeyboard()

    }

}