package com.example.computec.breakfast.ui.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.abdallah.hadanadataentry.R
import com.example.computec.eltadreb.ui.base.MvpView
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.progress_dialog.*
import timber.log.Timber


abstract class BaseFragment : Fragment(), MvpView {

    var baseActivity: BaseActivity? = null
    var fragmentView: ViewGroup? = null
    lateinit var title: String

//    private var mProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_base, container, false)
        try {
            fragmentView = LayoutInflater.from(activity).inflate(getFragmentView(), container, false) as ViewGroup
            view?.findViewById<ViewGroup>(R.id.layout_base_view)?.addView(fragmentView)
        } catch (e: Exception) {
            Timber.e(e.message.toString())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(fragmentView)
    }

    protected abstract fun getFragmentView(): Int

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun showLoading() {
//        hideLoading()
//        mProgressDialog = CommonUtils.showLoadingDialog(this.context!!)
        errorView?.visibility = View.GONE
        progressL?.visibility = View.VISIBLE
        fragmentView?.visibility = View.GONE
    }

    override fun hideLoading() {
//        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
////            mProgressDialog!!.cancel()
//        }
        errorView?.visibility = View.GONE
        progressL?.visibility = View.GONE
        fragmentView?.visibility = View.VISIBLE
    }

    override fun onError(message: String?) {
        baseActivity?.onError(message)
    }

    override fun onError(@StringRes resId: Int) {
        baseActivity?.onError(resId)
    }

    override fun onError(textString: String, actionString: String?, icon: Drawable?,
                         actionListener: View.OnClickListener?) {
        progressL.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        fragmentView?.visibility = View.GONE
        if (actionString != null) {
            errorActionB.setOnClickListener(actionListener)
            errorActionB.visibility = View.VISIBLE
        }
        if (icon != null) {
            errorIcon?.setImageDrawable(icon)
        }
        errorText.text = textString
        errorActionB.text = actionString
    }

    override fun onError(@StringRes errorTextRes: Int,
                         @StringRes errorActionRes: Int,
                         @DrawableRes errorIcon: Int,
                         errorActionListener: View.OnClickListener?) {
        val errorActionString = if (errorActionRes == 0) null else resources.getString(errorActionRes)
        val errorActionIcon = if (errorIcon == 0) null else ContextCompat.getDrawable(this!!.baseActivity!!, errorIcon)
        onError(resources.getString(errorTextRes),
                errorActionString,
                errorActionIcon,
                errorActionListener)
    }

    override fun showMessage(message: String?) {
        baseActivity?.showMessage(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        baseActivity?.showMessage(resId)
    }

    override fun isNetworkConnected(): Boolean = baseActivity!!.isNetworkConnected()

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun hideKeyboard() {
        baseActivity?.hideKeyboard()
    }

    override fun openActivityOnTokenExpire() {
        baseActivity?.openActivityOnTokenExpire()
    }

    protected abstract fun setUp(view: View?)

    override fun onDestroy() {
        super.onDestroy()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}
