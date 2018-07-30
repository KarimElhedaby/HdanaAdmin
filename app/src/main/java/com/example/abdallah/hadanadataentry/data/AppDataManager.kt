package com.example.abdallah.hadanadataentry.data

import com.example.abdallah.hadanadataentry.data.firebase.AppFirebaseHelper
import com.example.abdallah.hadanadataentry.data.firebase.FirebaseHelper
import com.example.abdallah.hadanadataentry.model.ClassModel
import com.example.abdallah.hadanadataentry.model.Kid
import com.example.abdallah.hadanadataentry.ui.base.BaseLisener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber


class AppDataManager : DataManager {


    private var firebaseAppHelper: FirebaseHelper = AppFirebaseHelper()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()


    override fun signUpTeacher(email: String, password: String,classesRef:List<String>, lisener: BaseLisener<String, String>) {
        firebaseAppHelper.signUpTeacher(email, password,classesRef)
                .addOnCompleteListener({
                    val myRef = database.getReference("teachers")

                    with(myRef.child(it.result.user.uid)){
                        child("email").setValue(it.result.user.email)
                        child("classes").setValue(classesRef)
                        lisener.onSuccess(it.result.toString())
                    }
                }).addOnFailureListener({
                    lisener.onFail(it.message.toString())
                })
    }

    override fun loginTeacher(email: String, password: String, lisener: BaseLisener<String, String>) {

        firebaseAppHelper.loginTeacher(email, password).addOnCompleteListener(OnCompleteListener {

            if (it.isComplete) {

                lisener.onSuccess(it.getResult().user.uid)
            } else {
                lisener.onFail(it.exception?.message!!)
            }

        }).addOnFailureListener({
            lisener.onFail(it.message.toString())
        })

    }

    override fun signUpParent(email: String, password: String, lisener: BaseLisener<String, String>) {
        firebaseAppHelper.signUpParent(email, password)
                .addOnCompleteListener({
                    val myRef = database.getReference("parents")

                    myRef.child(it.result.user.uid).child("email").setValue(it.result.user.email)
                    lisener.onSuccess(it.result.toString())
                }).addOnFailureListener({
                    lisener.onFail(it.message.toString())
                })
    }

    override fun addClass(classModel: ClassModel, lisener: BaseLisener<String, String>) {
        firebaseAppHelper.addClass(classModel)?.addOnSuccessListener {
            lisener.onSuccess("succes")

            Timber.d("success")

        }?.addOnFailureListener {
            lisener.onFail(it.message.toString())
            Timber.d(it.message.toString() + "fail")

        }

    }

    override fun addKid(kid: Kid, lisener: BaseLisener<String, String>) {
        firebaseAppHelper.addKid(kid)?.addOnSuccessListener {
            lisener.onSuccess("kid added successfully")
            Timber.d("Kid added")

        }?.addOnFailureListener {
            lisener.onFail(it.message.toString())
            Timber.d("Kid not  added")

        }
    }


    override fun loginParent(email: String, password: String, lisener: BaseLisener<String, String>) {


    }
}