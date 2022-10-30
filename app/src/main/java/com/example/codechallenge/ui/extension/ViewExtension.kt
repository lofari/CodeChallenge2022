package com.example.codechallenge.ui.extension

import android.view.View

fun View.fadeIn() {
    this.animate().apply {
        alpha(1f)
        duration = 500
    }.start()
}
