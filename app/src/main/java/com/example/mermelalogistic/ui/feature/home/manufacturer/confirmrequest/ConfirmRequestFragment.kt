package com.example.mermelalogistic.ui.feature.home.manufacturer.confirmrequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.core.ui.datapicker.DateTimePickerDialog
import com.example.mermelalogistic.databinding.FragmentConfirmRequestBinding
import java.util.Calendar

class ConfirmRequestFragment : BaseFragment<FragmentConfirmRequestBinding,ConfirmRequestViewModel>(
    FragmentConfirmRequestBinding::inflate
) {
    override val viewModel: ConfirmRequestViewModel by viewModels()


    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.toolbar.tvToolbarTitle.text = "Sipariş Onay"
        setEstArrDatePicker()
        setEstDepDatePicker()
    }

    override fun observeEvents() {

    }

    private fun setEstDepDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))

        binding.llEstimatedDepDate.setOnClickListener {
            val dialog = DateTimePickerDialog(
                requireContext(),
                calendar,
            ) { selectedDate ->
                binding.tvEstDepDate.text = selectedDate
                binding.tvEstDepDate
                    .setTextColor(
                        ResourcesCompat.getColor(resources, R.color.black, null)
                    )
            }
            dialog.datePicker.maxDate = calendar.timeInMillis
            dialog.show()
        }
    }

    private fun setEstArrDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))

        binding.llEstArrDate.setOnClickListener {
            val dialog = DateTimePickerDialog(
                requireContext(),
                calendar,
            ) { selectedDate ->
                binding.tvEstArrDate.text = selectedDate
                binding.tvEstArrDate
                    .setTextColor(
                        ResourcesCompat.getColor(resources, R.color.black, null)
                    )
            }
            dialog.datePicker.maxDate = calendar.timeInMillis
            dialog.show()
        }
    }
}