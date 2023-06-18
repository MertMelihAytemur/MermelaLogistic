package com.example.mermelalogistic.ui.feature.home.customer.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.databinding.FragmentOrderProductBinding

class OrderProductFragment : BaseFragment<FragmentOrderProductBinding,OrderProductViewModel>(
    FragmentOrderProductBinding::inflate
) {
    override val viewModel: OrderProductViewModel by viewModels()

    override fun onCreateFinished() {

    }

    override fun initListeners() {

    }

    override fun observeEvents() {

    }

}