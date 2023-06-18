package com.example.mermelalogistic.core.ui.timelineview

/**
 *Created by Mert Melih Aytemur on 6/15/2023.
 */
data class TimelineItem(
    val outsideColor : String,
    val insideColor : String,
    val text : String,
    val textColor : String,
    val startLineColor : String? = null,
    val endLineColor : String? = null
)
