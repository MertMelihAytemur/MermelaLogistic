package com.example.mermelalogistic.ext

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_LONG) {
    message?.let {
        Toast.makeText(requireContext(), it, duration).show()
    }
}