package com.example.abdallah.hadanadataentry.ui.addparent

import com.example.abdallah.hadanadataentry.ui.base.BaseLisener
import com.example.abdallah.hadanadataentry.ui.data.AppDataManager
import com.example.computec.eltadreb.ui.base.BasePresenter

class ParentPresenter<V : ParentContract.View> : BasePresenter<V>(), ParentContract.Presenter<V> {

    private var dataManager: AppDataManager = AppDataManager()

    override fun addParent(email: String, password: String) {
        dataManager.signUpParent(email, password,
                object : BaseLisener<String, String> {
                    override fun onSuccess(data: String) {
                        mvpView?.onParentAdded(data)
                    }

                    override fun onFail(error: String) {
                        mvpView?.onParentAdded(error)

                    }
                })
    }

}