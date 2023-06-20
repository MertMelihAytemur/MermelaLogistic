package com.example.mermelalogistic.ui.feature.home.customer


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.data.local.home.customer.CustomerWarehouseUiModel
import com.example.mermelalogistic.databinding.FragmentCustomerHomeBinding
import com.example.mermelalogistic.ext.observe
import com.example.mermelalogistic.ui.feature.home.customer.adapter.CustomerWarehouseItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerHomeFragment : BaseFragment<FragmentCustomerHomeBinding,CustomerHomeViewModel>(
    FragmentCustomerHomeBinding::inflate
) {
    override val viewModel: CustomerHomeViewModel by viewModels()

    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.btnWarehouses.setOnClickListener {
            viewModel.navigateToManufacturersInfoFragment()
        }

        binding.btnRequests.setOnClickListener {
            viewModel.navigateToCustomerRequests()
        }
    }

    override fun observeEvents() {
        lifecycleScope.launch {
            observe(viewModel.warehouseInfo){
                if(it.isNotEmpty()){
                    setWarehouseRecyclerViewAdapter(it)
                }
            }
            observe(viewModel.factoryInfo){
                it?.let { factoryInfo ->
                    binding.toolbar.tvToolbarTitle.text = "${factoryInfo.name} Depo Bilgileri"
                }
            }
        }
    }

    private fun setWarehouseRecyclerViewAdapter(warehouseInfo: List<CustomerWarehouseUiModel>) {
        val mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvWarehouseItem.layoutManager = mLayoutManager

        binding.rvWarehouseItem.adapter =
            CustomerWarehouseItemAdapter(requireContext(),warehouseInfo)
    }
}