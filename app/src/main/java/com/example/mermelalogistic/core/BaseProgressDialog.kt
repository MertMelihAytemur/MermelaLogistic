package com.example.mermelalogistic.core

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.mermelalogistic.R

class BaseProgressDialog(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
        makeBackgroundTransparent()
    }

    private fun makeBackgroundTransparent(){
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}