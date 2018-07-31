package com.example.abdallah.hadanadataentry.ui.addclass

import com.example.abdallah.hadanadataentry.model.ClassModel
import com.example.computec.eltadreb.ui.base.MvpPresenter
import com.example.computec.eltadreb.ui.base.MvpView

interface ClassContract {

    interface View :MvpView{
        fun onClassAdded(msg : String)
    }

    interface Presenter<V :View> :MvpPresenter<V>{
        fun addClass(classModel : ClassModel)

    }
}