package com.example.abdallah.hadanadataentry.ui.addclass

import com.example.abdallah.hadanadataentry.ui.base.BaseLisener
import com.example.abdallah.hadanadataentry.ui.data.AppDataManager
import com.example.abdallah.hadanadataentry.ui.model.ClassModel
import com.example.computec.eltadreb.ui.base.BasePresenter
import timber.log.Timber

class ClassPresenter<V : ClassContract.View> : BasePresenter<V>(), ClassContract.Presenter<V> {

    private var dataManager: AppDataManager = AppDataManager()

    override fun addClass(classModel: ClassModel) {
        dataManager.addClass(classModel,
                object : BaseLisener<String, String> {
                    override fun onSuccess(data: String) {
                        mvpView?.onClassAdded(data)
                        Timber.d(data + "CLASS PRESENTER")
                    }

                    override fun onFail(error: String) {
                        mvpView?.onClassAdded(error)
                        Timber.d(error + "CLASS PRESENTER")
                    }
                })
    }

}