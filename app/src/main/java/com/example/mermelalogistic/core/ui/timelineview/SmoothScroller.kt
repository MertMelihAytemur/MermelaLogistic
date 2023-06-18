package com.example.mermelalogistic.core.ui.timelineview

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 *Created by Mert Melih Aytemur on 6/15/2023.
 */
class SmoothScroller(context : Context,
                     private val milliSecondsPerInch : Float = MILLISECONDS_PER_INCH
) : LinearSmoothScroller(context) {

    companion object{
        const val MILLISECONDS_PER_INCH = 240f // default is 25f (bigger = slower)
    }

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
        return (milliSecondsPerInch / displayMetrics!!.density)
    }

}