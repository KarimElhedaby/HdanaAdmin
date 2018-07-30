package com.example.abdallah.hadanadataentry.ui.data.firebase

import com.example.abdallah.hadanadataentry.ui.model.ClassModel
import com.example.abdallah.hadanadataentry.ui.model.Kid
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

class AppFirebaseHelper : FirebaseHelper {

    private val KIDS = "kids"
    private val TEACHER = "teacher"
    private val PARENT = "parent"
    private val SUBJECTS = "subjects"
    private val CLASSES = "classes"
    private val GALLERY = "gallery"
    private val NEWS = "news"

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()


    override fun signUpTeacher(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override fun loginTeacher(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    override fun signUpParent(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override fun loginParent(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    override fun addClass(classModel: ClassModel) : Task<Void>{
        return database.getReference("classes").push().setValue(classModel)

    }
    override fun addKid(kid: Kid): Task<Void>? {
        Timber.d("APPFIREBASEHELPER kids ")
        return database.getReference("kids").push().setValue(kid)
    }

}
