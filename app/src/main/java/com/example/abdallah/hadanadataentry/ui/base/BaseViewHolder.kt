package com.example.computec.eltadreb.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var currentPosition: Int = 0

    protected abstract fun clear()

    fun onBind(position: Int) {
        currentPosition = position
        clear()
    }
}
