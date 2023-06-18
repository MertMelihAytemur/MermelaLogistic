package com.example.mermelalogistic.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyBoard() {
    var view: View? = currentFocus

    if (view == null) {
        view = View(this)
    }
    getInputMethodManager().hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.getInputMethodManager() =
    this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager