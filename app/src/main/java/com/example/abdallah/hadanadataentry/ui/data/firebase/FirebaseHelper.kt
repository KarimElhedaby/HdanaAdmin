package com.example.abdallah.hadanadataentry.ui.data.firebase

import com.example.abdallah.hadanadataentry.ui.base.BaseLisener
import com.example.abdallah.hadanadataentry.ui.model.ClassModel
import com.example.abdallah.hadanadataentry.ui.model.Kid
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot

interface FirebaseHelper {
    fun signUpParent(email: String, password: String): Task<AuthResult>

    fun loginParent(email: String, password: String): Task<AuthResult>

    fun signUpTeacher(email: String, password: String): Task<AuthResult>

    fun loginTeacher(email: String, password: String): Task<AuthResult>

    fun addClass(classModel: ClassModel ) : Task<Void>?

    fun addKid(kid: Kid) : Task<Void>?

}