package com.example.abdallah.hadanadataentry.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.ui.addclass.AddClassActivity
import com.example.abdallah.hadanadataentry.ui.addkid.AddKidActivity
import com.example.abdallah.hadanadataentry.ui.addparent.AddParentActivity
import com.example.abdallah.hadanadataentry.ui.addteacher.AddTeacherActivity
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getActivityView(): Int {
        return R.layout.activity_main
    }

    override fun afterInflation(savedInstance: Bundle?) {
        addTeacherBTN.setOnClickListener {
            startActivity(Intent(
                    applicationContext,
                    AddTeacherActivity::class.java
            ))
        }

        addParentBTN.setOnClickListener {
            startActivity(Intent(
                    applicationContext,
                    AddParentActivity::class.java
            ))
        }

        addClassBTN.setOnClickListener {
            startActivity(Intent(
                    applicationContext,
                    AddClassActivity::class.java
            ))
        }

        addKidBTN.setOnClickListener {
            startActivity(Intent(
                    applicationContext,
                    AddKidActivity::class.java
            ))
        }
    }


}
