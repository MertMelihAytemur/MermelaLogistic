package com.example.mermelalogistic.ext

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun Fragment.safeNavigateFromNavController(directions: NavDirections) {
    val navController = findNavController()
    val destination = navController.currentDestination as? FragmentNavigator.Destination
    if (javaClass.name == destination?.className) {
        try {
            navController.navigate(directions)
        } catch (e: IllegalArgumentException) {

        }
    }
}