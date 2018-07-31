package com.example.abdallah.hadanadataentry.model

data class ClassModel(var name:String = " ", var teachers: MutableMap<String, TeacherSubjectModel>? = null, var kids: List<String>? = null){
    override fun toString(): String {
        return "$name"
    }
}

