package com.example.abdallah.hadanadataentry.data

import com.example.abdallah.hadanadataentry.ui.base.BaseLisener
import com.example.abdallah.hadanadataentry.model.ClassModel
import com.example.abdallah.hadanadataentry.model.Kid

interface DataManager {
    fun signUpTeacher(email: String, password: String, lisener: BaseLisener<String, String>)

    fun loginTeacher(email: String, password: String, lisener: BaseLisener<String, String>)

    fun signUpParent(email: String, password: String, lisener: BaseLisener<String, String>)

    fun loginParent(email: String, password: String, lisener: BaseLisener<String, String>)

    fun addClass(classModel: ClassModel, lisener: BaseLisener<String, String>)

    fun addKid(kid: Kid,lisener: BaseLisener<String, String> )
}