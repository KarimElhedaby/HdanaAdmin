package com.example.abdallah.hadanadataentry.ui.addkid

import com.example.abdallah.hadanadataentry.ui.base.BaseLisener
import com.example.abdallah.hadanadataentry.ui.data.AppDataManager
import com.example.abdallah.hadanadataentry.ui.model.ClassModel
import com.example.abdallah.hadanadataentry.ui.model.Kid
import com.example.computec.eltadreb.ui.base.BasePresenter
import timber.log.Timber

class KidPresenter<V : KidContract.View> : BasePresenter<V>(), KidContract.Presenter<V> {

    private var dataManager: AppDataManager = AppDataManager()

    override fun addKid(kid: Kid) {
        dataManager.addKid(kid,
                object : BaseLisener<String, String> {
                    override fun onSuccess(data: String) {
                        mvpView?.onKidAdded(data)
                        Timber.d(data + "kid PRESENTER")
                    }

                    override fun onFail(error: String) {
                        mvpView?.onKidAdded(error)
                        Timber.d(error + "kid PRESENTER")
                    }
                })
    }

}