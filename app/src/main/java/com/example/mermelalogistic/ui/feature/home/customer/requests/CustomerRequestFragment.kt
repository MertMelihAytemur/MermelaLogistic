package com.example.mermelalogistic.ui.feature.home.customer.requests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.core.ui.timelineview.TimelineItem
import com.example.mermelalogistic.databinding.FragmentCustomerRequestBinding


class CustomerRequestFragment : BaseFragment<FragmentCustomerRequestBinding,CustomerRequestViewModel>(
    FragmentCustomerRequestBinding::inflate
) {
    override val viewModel: CustomerRequestViewModel by viewModels()

    override fun onCreateFinished() {
        binding.timelineViewShipment.setItems(createShipmentTimelineItems())
        binding.timelineViewShipment.startTooltipAnimation(600,200f)
    }

    override fun initListeners() {
        binding.toolbar.tvToolbarTitle.text = "Sipariş Detay"
    }

    override fun observeEvents() {

    }

    private fun createShipmentTimelineItems(): List<TimelineItem> {
        //active
        val item1 = TimelineItem(
            outsideColor = colorWhite,
            insideColor = colorGreen,
            text = "Siparişiniz Alındı",
            textColor = colorGreen,
            endLineColor = colorGreen
        )

        //active
        val item2 = TimelineItem(
            outsideColor = colorWhite,
            insideColor = colorGreen,
            text = "Siparişiniz Hazırlanıyor",
            textColor = colorGreen,
            startLineColor = colorGreen,
            endLineColor = colorGreen
        )

        //passive
        val item3 = TimelineItem(
            outsideColor = colorWhite,
            insideColor = colorGreen,
            text = "Kargoya Verildi",
            textColor = colorGreen,
            startLineColor = colorGreen,
            endLineColor = colorGray
        )

        //passive
        val item4 = TimelineItem(
            outsideColor = colorGray,
            insideColor = colorWhite,
            text = "Teslimat Noktasında",
            textColor = colorGray,
            startLineColor = colorGray,
            endLineColor = colorGray
        )

        val item5 = TimelineItem(
            outsideColor = colorGray,
            insideColor = colorWhite,
            text = "Teslim Edildi",
            textColor = colorGray,
            startLineColor = colorGray
        )
        return listOf(item1, item2, item3, item4, item5)
    }

    companion object {
        private const val colorWhite = "#FFFFFF"
        private const val colorGray = "#E5E5E5"
        private const val colorRed = "#BB0000"
        private const val colorGreen = "#0BC15C"
    }

}