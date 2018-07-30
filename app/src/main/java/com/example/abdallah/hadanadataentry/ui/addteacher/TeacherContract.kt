package com.example.abdallah.hadanadataentry.ui.addteacher

import com.example.computec.eltadreb.ui.base.MvpPresenter
import com.example.computec.eltadreb.ui.base.MvpView

interface TeacherContract {

    interface View :MvpView{
        fun onTeacherAdded(msg : String)
    }

    interface Presenter<V :View> :MvpPresenter<V>{
        fun addTeacher(email : String , password : String,classesRef:List<String>)

    }
}