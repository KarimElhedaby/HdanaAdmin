package com.example.computec.eltadreb.ui.base

import android.util.Log
import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.model.ApiError
import com.example.computec.eltadreb.utils.AppConstants
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import javax.net.ssl.HttpsURLConnection

open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    var mvpView: V? = null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        mvpView = null
    }

    fun isViewAttached(): Boolean = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    override fun handleApiError(error: ANError?) {

        if (error == null || error.errorBody == null) {
            mvpView?.onError(R.string.api_default_error)
            return
        }

        if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.CONNECTION_ERROR) {
            mvpView?.onError(R.string.connection_error)
            return
        }

        if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.REQUEST_CANCELLED_ERROR) {
            mvpView?.onError(R.string.api_retry_error)
            return
        }

        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        try {
            val apiError = gson.fromJson(error.errorBody, ApiError::class.java)

            if (apiError?.message == null) {
                mvpView?.onError(R.string.api_default_error)
                return
            }

            when (error.errorCode) {
                HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {
                    setUserAsLoggedOut()
                    mvpView?.openActivityOnTokenExpire()
                    mvpView?.onError(apiError.message)
                }
                HttpsURLConnection.HTTP_INTERNAL_ERROR,
                HttpsURLConnection.HTTP_NOT_FOUND -> mvpView!!.onError(apiError.message)
                else -> mvpView!!.onError(apiError.message)
            }
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "handleApiError", e)
            mvpView?.onError(R.string.api_default_error)
        } catch (e: NullPointerException) {
            Log.e(TAG, "handleApiError", e)
            mvpView?.onError(R.string.api_default_error)
        }

    }

    override fun setUserAsLoggedOut() {

    }

    class MvpViewNotAttachedException :
            RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")

    companion object {
        private val TAG = "BasePresenter"
    }
}
