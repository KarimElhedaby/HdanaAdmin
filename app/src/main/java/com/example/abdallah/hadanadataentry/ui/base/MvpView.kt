package com.example.computec.eltadreb.ui.base

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.View

interface MvpView {

    fun showLoading()

    fun hideLoading()

    fun openActivityOnTokenExpire()

    fun onError(@StringRes resId: Int)

    fun onError(message: String?)

    fun onError(textString: String, actionString: String?, icon: Drawable?,
                actionListener: View.OnClickListener?)

    fun onError(@StringRes errorTextRes: Int,
                @StringRes errorActionRes: Int,
                @DrawableRes errorIcon: Int,
                errorActionListener: View.OnClickListener?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()
}
