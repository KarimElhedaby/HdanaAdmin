package com.example.abdallah.hadanadataentry.ui.addparent

import com.example.computec.eltadreb.ui.base.MvpPresenter
import com.example.computec.eltadreb.ui.base.MvpView

interface ParentContract {

    interface View :MvpView{
        fun onParentAdded(msg : String)
    }

    interface Presenter<V :View> :MvpPresenter<V>{
        fun addParent(email : String , password : String,kidsRef : ArrayList<String>)

    }
}