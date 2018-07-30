package com.example.abdallah.hadanadataentry.ui.addparent

import android.os.Bundle
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_parent.*

class AddParentActivity : BaseActivity() ,ParentContract.View{


    override fun getActivityView(): Int = R.layout.activity_add_parent

    lateinit var presenter : ParentContract.Presenter<AddParentActivity>
    override fun afterInflation(savedInstance: Bundle?) {
        presenter = ParentPresenter()
        presenter.onAttach(this)
//        presenter.addTeacher()

        addParentBTN.setOnClickListener {
            showLoading()
            presenter.addParent(parentEmailET.text.toString(),parentPasswordET.text.toString())
        }
    }

    override fun onParentAdded(msg: String) {
        hideLoading()
        Toast.makeText(applicationContext,""+msg,Toast.LENGTH_LONG).show()

    }

}
