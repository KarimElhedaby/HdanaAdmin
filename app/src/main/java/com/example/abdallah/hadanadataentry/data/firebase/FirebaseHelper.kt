package com.example.abdallah.hadanadataentry.data.firebase

import com.example.abdallah.hadanadataentry.model.ClassModel
import com.example.abdallah.hadanadataentry.model.Kid
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseHelper {
    fun signUpParent(email: String, password: String,kidsRef : ArrayList<String>): Task<AuthResult>

    fun loginParent(email: String, password: String): Task<AuthResult>

    fun signUpTeacher(email: String, password: String,classesRef:List<String>): Task<AuthResult>

    fun loginTeacher(email: String, password: String): Task<AuthResult>

    fun addClass(classModel: ClassModel ) : Task<Void>?

    fun addKid(kid: Kid) : Task<Void>?

}