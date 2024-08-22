package com.example.marvelmovie.utils

import android.view.LayoutInflater
import android.view.View

val View.layoutInflater get() = LayoutInflater.from(context)

fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}