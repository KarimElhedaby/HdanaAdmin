package com.example.abdallah.hadanadataentry.model

data class Kid(var name: String= "", var img: String = "", val stars: Int = 0, var kid_class: String= ""){
    override fun toString(): String {
        return "$name"
    }
}