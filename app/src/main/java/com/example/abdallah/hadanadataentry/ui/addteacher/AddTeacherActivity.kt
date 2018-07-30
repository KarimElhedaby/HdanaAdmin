package com.example.abdallah.hadanadataentry.ui.addteacher

import android.os.Bundle
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_teacher.*

class AddTeacherActivity : BaseActivity(), TeacherContract.View {

    override fun getActivityView(): Int = R.layout.activity_add_teacher

    lateinit var presenter : TeacherContract.Presenter<AddTeacherActivity>
    override fun afterInflation(savedInstance: Bundle?) {
        presenter = TeacherPresenter()
        presenter.onAttach(this)
//        presenter.addTeacher()

        addTeacherBTN.setOnClickListener {
            showLoading()
            presenter.addTeacher(teacherEmailET.text.toString(),teacherPasswordET.text.toString())
        }
    }

    override fun onTeacherAdded(msg: String) {
        hideLoading()
        Toast.makeText(applicationContext,""+msg,Toast.LENGTH_LONG).show()

    }
}
