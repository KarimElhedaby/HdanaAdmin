package com.example.abdallah.hadanadataentry.ui.addclass

import android.os.Bundle
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.model.ClassModel
import com.hamza.solutions.kolo.ui.base.BaseActivity

class AddClassActivity : BaseActivity(),ClassContract.View {

    override fun getActivityView(): Int = R.layout.activity_add_class

    lateinit var presenter : ClassContract.Presenter<AddClassActivity>
    override fun afterInflation(savedInstance: Bundle?) {
        presenter = ClassPresenter()
        presenter.onAttach(this)

        var techers:MutableMap<String,String> = mutableMapOf(Pair("sdgsd","dsg"), Pair("fsdfs","sdfsd"))

        var classModel  = ClassModel("Kemo",techers, listOf("sdf0","sdf","sdf"))
        presenter.addClass(classModel)

    }

    override fun onClassAdded(msg: String) {
        Toast.makeText(applicationContext,""+msg,Toast.LENGTH_LONG).show()
    }


}
