package com.example.abdallah.hadanadataentry.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Mohamed Fakhry on 12/03/2018.
 */
data class ApiError(private val errorCode: Int,
                    @Expose
                    @SerializedName("status_code")
                    private val statusCode: String?,
                    @Expose
                    @SerializedName("message")
                    val message: String?)