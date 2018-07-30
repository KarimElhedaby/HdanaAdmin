package com.example.computec.eltadreb.ui.base

import android.annotation.TargetApi
import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.ViewGroup

abstract class BaseSubView : ViewGroup, SubMvpView {

    private var mParentMvpView: MvpView? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun attachParentMvpView(mvpView: MvpView) {
        mParentMvpView = mvpView
    }

    override fun showLoading() {
        mParentMvpView!!.showLoading()
    }

    override fun hideLoading() {
        mParentMvpView!!.hideLoading()
    }

    override fun onError(@StringRes resId: Int) {
        mParentMvpView!!.onError(resId)
    }

    override fun onError(message: String?) {
        mParentMvpView!!.onError(message)
    }

    override fun showMessage(message: String?) {
        mParentMvpView!!.showMessage(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        mParentMvpView!!.showMessage(resId)
    }

    override fun hideKeyboard() {
        mParentMvpView!!.hideKeyboard()
    }

    override fun isNetworkConnected(): Boolean = mParentMvpView!!.isNetworkConnected()

    override fun openActivityOnTokenExpire() {
        mParentMvpView!!.openActivityOnTokenExpire()
    }

    protected abstract fun bindViewsAndSetOnClickListeners()

    protected abstract fun setUp()
}
