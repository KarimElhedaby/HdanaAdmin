package com.example.abdallah.hadanadataentry.ui.addteacher

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.model.ClassModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_teacher.*


class AddTeacherActivity : BaseActivity(), TeacherContract.View {

    var classesMap: MutableMap<String, ClassModel> = mutableMapOf()
    var classsRef: ArrayList<String> = arrayListOf()
    lateinit var choosenClass: String

    override fun getActivityView(): Int = R.layout.activity_add_teacher

    lateinit var presenter: TeacherContract.Presenter<AddTeacherActivity>

    override fun afterInflation(savedInstance: Bundle?) {
        presenter = TeacherPresenter()
        presenter.onAttach(this)
        getClasses()

        addTeacherBTN.setOnClickListener {
            showLoading()
            presenter.addTeacher(teacherEmailET.text.toString(), teacherPasswordET.text.toString(), classsRef)
        }
    }

    private fun getClasses() {

        FirebaseDatabase.getInstance().getReference("classes").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (c in p0?.children!!) {
                    classesMap.put(c.key.toString(), c.getValue(ClassModel::class.java)!!)
                }

                var spinnerData = ArrayAdapter<ClassModel>(applicationContext, android.R.layout.simple_spinner_dropdown_item)

                for (c in classesMap) {
                    spinnerData.add(c.value)
                }
                class_spinner.adapter = spinnerData

                class_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        choosenClass = parent?.getItemAtPosition(position).toString()
                        for (values in classesMap) {
                            if (parent?.getItemAtPosition(position) == values.value)
                                classsRef.add(values.key)
                        }
                    }
                }
            }
        })
    }

    override fun onTeacherAdded(msg: String) {
        hideLoading()
        Toast.makeText(applicationContext, "" + msg, Toast.LENGTH_LONG).show()
        classsRef.clear()
    }
}
