package com.example.abdallah.hadanadataentry.ui.addkid

import com.example.abdallah.hadanadataentry.model.Kid
import com.example.computec.eltadreb.ui.base.MvpPresenter
import com.example.computec.eltadreb.ui.base.MvpView

interface KidContract {

    interface View :MvpView{
        fun onKidAdded(msg : String)
    }

    interface Presenter<V :View> :MvpPresenter<V>{
        fun addKid(kid : Kid)

    }
}