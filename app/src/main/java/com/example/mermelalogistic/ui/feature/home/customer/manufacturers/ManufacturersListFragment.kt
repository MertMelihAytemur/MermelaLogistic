package com.example.mermelalogistic.ui.feature.home.customer.manufacturers


import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.data.local.home.customer.CustomerManufacturersUiModel
import com.example.mermelalogistic.databinding.FragmentManufacturersListBinding
import com.example.mermelalogistic.ext.observe
import com.example.mermelalogistic.ext.toast
import com.example.mermelalogistic.ui.feature.home.customer.adapter.CustomerManufacturersListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManufacturersListFragment : BaseFragment<FragmentManufacturersListBinding,ManufacturersListViewModel>(
    FragmentManufacturersListBinding::inflate
) {
    override val viewModel: ManufacturersListViewModel by viewModels()

    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.toolbar.tvToolbarTitle.text = getString(R.string.manufacturers)
    }

    override fun observeEvents() {
        observe(viewModel.manufacturerList){
            setWarehouseRecyclerViewAdapter(it)
        }
    }

    private fun setWarehouseRecyclerViewAdapter(warehouseInfo: List<CustomerManufacturersUiModel>) {
        val mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvManufacturers.layoutManager = mLayoutManager

        binding.rvManufacturers.adapter =
            CustomerManufacturersListAdapter(requireContext(),warehouseInfo,::navigateToFactoryDetail)
    }

    private fun navigateToFactoryDetail(factoryId : String){
        viewModel.navigateToFactoryDetail(factoryId)
    }
}