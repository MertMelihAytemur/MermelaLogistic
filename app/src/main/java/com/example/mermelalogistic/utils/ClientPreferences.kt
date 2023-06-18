package com.example.mermelalogistic.utils

import android.content.Context
import com.example.mermelalogistic.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ClientPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val preferencesManager =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    private val editor = preferencesManager.edit()

    fun setMFactoryId(factoryId : String) = editor.apply {
        putString(M_FACTORY,factoryId )
    }.apply()

    fun setCFactoryId(factoryId : String) = editor.apply {
        putString(C_FACTORY,factoryId )
    }.apply()

    fun getMFactoryId(): String? = preferencesManager.getString(M_FACTORY,"default")
    fun getCFactoryId() : String? = preferencesManager.getString(C_FACTORY,"default")

    fun clearAll() = editor.clear().commit()

    companion object {
        private const val M_FACTORY = "default"
        private const val C_FACTORY = "default"
    }
}