package com.example.computec.eltadreb.utils

import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.View
import com.example.abdallah.hadanadataentry.R
import com.example.computec.breakfast.ui.base.BaseFragment
import com.hamza.solutions.kolo.ui.base.BaseActivity


fun BaseActivity.replaceFragmentToActivity(fragment: Fragment, isSaved: Boolean = false, transitionView: View? = null) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    if (isSaved) transaction.addToBackStack(null)
    transitionView?.let {
        transaction.addSharedElement(it, ViewCompat.getTransitionName(it)!!)
    }
    transaction.commit()
}

fun BaseActivity.addFragmentToActivity(fragment: Fragment, isSaved: Boolean = false) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(R.id.container, fragment)
    if (isSaved) transaction.addToBackStack(null)
    transaction.commit()
}

fun BaseFragment.replaceFragment(fragment: Fragment, isSaved: Boolean = false) {
    val transaction = childFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    if (isSaved) transaction.addToBackStack(null)
    transaction.commit()
}