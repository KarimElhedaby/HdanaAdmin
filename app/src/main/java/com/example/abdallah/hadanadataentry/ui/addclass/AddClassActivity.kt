package com.example.abdallah.hadanadataentry.ui.addclass

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.model.ClassModel
import com.example.abdallah.hadanadataentry.model.Kid
import com.example.abdallah.hadanadataentry.model.TeacherModel
import com.example.abdallah.hadanadataentry.model.TeacherSubjectModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_class.*

class AddClassActivity : BaseActivity(), ClassContract.View {

    override fun getActivityView(): Int = R.layout.activity_add_class

    var teacherMap: MutableMap<String, TeacherModel> = mutableMapOf()
    var teacherRef: MutableMap<String, TeacherSubjectModel> = mutableMapOf()
    lateinit var choosenTeacher: String
    var teacherSubject: TeacherSubjectModel = TeacherSubjectModel()

    var kidMap: MutableMap<String, Kid> = mutableMapOf()
    var kidRef: ArrayList<String> = arrayListOf()
    lateinit var choosenKid: String

    lateinit var presenter: ClassContract.Presenter<AddClassActivity>

    override fun afterInflation(savedInstance: Bundle?) {
        presenter = ClassPresenter()
        presenter.onAttach(this)

        getTeachers()
        getKids()

        addclassBTN.setOnClickListener {
            showLoading()
            var classModel = ClassModel(classNameET.text.toString(), teacherRef, kidRef)
            presenter.addClass(classModel)
        }

        clearBTN.setOnClickListener {
            teacherRef.clear()
            kidRef.clear()
        }
    }

    private fun getTeachers() {

        FirebaseDatabase.getInstance().getReference("teachers").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (c in p0?.children!!) {

                    teacherMap.put(c.key.toString(), c.getValue(TeacherModel::class.java)!!)
                }

                var spinnerData = ArrayAdapter<TeacherModel>(applicationContext, android.R.layout.simple_spinner_dropdown_item)

                for (c in teacherMap) {
                    spinnerData.add(c.value)
                }
                classTeacherSpiner.adapter = spinnerData

                classTeacherSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        choosenTeacher = parent?.getItemAtPosition(position).toString()
                        for (values in teacherMap) {
                            if (parent?.getItemAtPosition(position) == values.value) {

                                teacherSubject.Subject = subjectNameET.text.toString()
                                teacherSubject.teacher_id = values.key
                                teacherRef.put(values.key + "_" + teacherSubject.Subject, teacherSubject)

                            }
                        }
                    }
                }
            }
        })
    }


    private fun getKids() {

        FirebaseDatabase.getInstance().getReference("kids").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (c in p0?.children!!) {

                    kidMap.put(c.key.toString(), c.getValue(Kid::class.java)!!)
                }

                var KidSpinner = ArrayAdapter<Kid>(applicationContext, android.R.layout.simple_spinner_dropdown_item)

                for (c in kidMap) {
                    KidSpinner.add(c.value)
                }
                classKidSpiner.adapter = KidSpinner

                classKidSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        choosenKid = parent?.getItemAtPosition(position).toString()
                        for (values in kidMap) {
                            if (parent?.getItemAtPosition(position) == values.value)
                                kidRef.add(values.key)
                        }
                    }
                }
            }
        })
    }

    override fun onClassAdded(msg: String) {

        hideLoading()
        Toast.makeText(applicationContext, "" + msg, Toast.LENGTH_LONG).show()
        teacherRef.clear()
        kidRef.clear()
    }

}
