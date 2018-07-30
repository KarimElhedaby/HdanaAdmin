package com.example.abdallah.hadanadataentry.ui.base

interface BaseLisener<T, E> {

    fun onSuccess(data: T)

    fun onFail(error: E)
}