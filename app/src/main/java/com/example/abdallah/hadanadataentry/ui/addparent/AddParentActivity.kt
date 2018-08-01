package com.example.abdallah.hadanadataentry.ui.addparent

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.model.Kid
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_parent.*
import kotlinx.android.synthetic.main.activity_add_teacher.*
import timber.log.Timber

class AddParentActivity : BaseActivity() ,ParentContract.View{

    var kidsMap: MutableMap<String, Kid> = mutableMapOf()
    lateinit var database: FirebaseDatabase
    var kidsRef : ArrayList<String> = arrayListOf()
    override fun getActivityView(): Int = R.layout.activity_add_parent

    lateinit var presenter : ParentContract.Presenter<AddParentActivity>
    override fun afterInflation(savedInstance: Bundle?) {
        presenter = ParentPresenter()
        presenter.onAttach(this)


        getKids()

        deleteKidsBTN.setOnClickListener {
            kidsRef.clear()
        }

        addParentBTN.setOnClickListener {
            showLoading()
            presenter.addParent(parentEmailET.text.toString(),parentPasswordET.text.toString(),kidsRef)
        }



        kidsSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                for (values in kidsMap) {
                    if (parent?.getItemAtPosition(position) == values.value)
                        kidsRef.add(values.key)
                }

                Toast.makeText(applicationContext,kidsRef.size.toString(),Toast.LENGTH_LONG).show()

            }
        }

    }

    private fun getKids() {
        database = FirebaseDatabase.getInstance()
        val classes = database.getReference("kids")

        classes.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Toast.makeText(applicationContext, p0?.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot?) {


                for (c in p0?.children!!) {
                    kidsMap?.put(c.key.toString(), c.getValue(Kid::class.java)!!)
                }

                var spinerData = ArrayAdapter<Kid>(applicationContext, android.R.layout.simple_spinner_dropdown_item)
                for (c in kidsMap) {
                    spinerData.add(c.value)
                }

                kidsSpiner.adapter = spinerData

                Timber.d(kidsMap.toString())

            }

        })

    }

    override fun onParentAdded(msg: String) {
        hideLoading()
        Toast.makeText(applicationContext,""+msg,Toast.LENGTH_LONG).show()

    }

}
