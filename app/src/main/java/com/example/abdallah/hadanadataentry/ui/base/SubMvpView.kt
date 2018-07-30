package com.example.computec.eltadreb.ui.base

interface SubMvpView : MvpView {

    fun onCreate()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun attachParentMvpView(mvpView: MvpView)
}
