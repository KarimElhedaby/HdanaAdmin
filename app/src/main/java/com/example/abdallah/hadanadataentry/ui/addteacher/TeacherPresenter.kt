package com.example.abdallah.hadanadataentry.ui.addteacher

import com.example.abdallah.hadanadataentry.ui.base.BaseLisener
import com.example.abdallah.hadanadataentry.ui.data.AppDataManager
import com.example.computec.eltadreb.ui.base.BasePresenter

class TeacherPresenter<V : TeacherContract.View> : BasePresenter<V>(), TeacherContract.Presenter<V> {

    private var dataManager: AppDataManager = AppDataManager()

    override fun addTeacher(email: String, password: String) {
        dataManager.signUpTeacher(email, password,
                object : BaseLisener<String, String> {
                    override fun onSuccess(data: String) {
                        mvpView?.onTeacherAdded(data)
                    }

                    override fun onFail(error: String) {
                        mvpView?.onTeacherAdded(error)

                    }
                })
    }

}