package com.example.abdallah.hadanadataentry.model

data class TeacherModel (var email:String = " ", var classes: ArrayList<String>? = null){
    override fun toString(): String {
        return "$email"
    }
}