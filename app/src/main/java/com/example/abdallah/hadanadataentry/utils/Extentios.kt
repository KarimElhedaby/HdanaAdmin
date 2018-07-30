package com.example.computec.eltadreb.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView?.loadImage(url: String?, error: Int = 0, placeholder: Int = 0) {
    Glide.with(this!!.context)
            .load(url)
            .into(this)
}


fun ImageView?.loadImage(url: Int?) {
    this?.context?.let {
        Glide.with(it)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
    }
}

//fun Double.formatToTwoPoint(): Double {
//    val df = DecimalFormat("0.00E0")
//    return df.format(this).toDouble()
//}

fun Double.format(point: Int): Double {
    val multi = Math.pow(10.0, point.toDouble())
    return Math.ceil(this * multi) / multi
}

