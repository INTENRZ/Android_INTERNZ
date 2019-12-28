package com.example.internz.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes stringId: Int) {
    Toast.makeText(this, this.getString(stringId), Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: String) {
    this.context?.let {
        Toast.makeText(it, msg, Toast.LENGTH_SHORT).show()
    }
}