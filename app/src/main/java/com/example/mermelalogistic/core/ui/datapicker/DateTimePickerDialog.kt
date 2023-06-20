package com.example.mermelalogistic.core.ui.datapicker

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.example.mermelalogistic.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 *Created by Mert Melih Aytemur on 6/20/2023.
 */
class DateTimePickerDialog(
    context: Context,
    calendar: Calendar,
    dateFormat: String = "dd/MM/yyyy",
    onDateSelected: (String) -> Unit
) : DatePickerDialog(
    context,
    R.style.DatePickerDialogTheme,
    CustomDateSelectedListener(calendar, onDateSelected, dateFormat),
    calendar.get(Calendar.YEAR),
    calendar.get(Calendar.MONTH),
    calendar.get(Calendar.DAY_OF_MONTH)
)

private class CustomDateSelectedListener(
    private val calendar: Calendar,
    private val onDateSelected: (String) -> Unit,
    private val dateFormat: String
) : DatePickerDialog.OnDateSetListener {
    override fun onDateSet(p0: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())

        onDateSelected(sdf.format(calendar.time))
    }
}