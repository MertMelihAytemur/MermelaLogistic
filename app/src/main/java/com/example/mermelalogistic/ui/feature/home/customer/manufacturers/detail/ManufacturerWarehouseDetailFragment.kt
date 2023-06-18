package com.example.mermelalogistic.ui.feature.home.customer.manufacturers.detail

import android.view.View
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.data.local.home.customer.RequestProductRequestModel
import com.example.mermelalogistic.data.local.home.manufacturer.ManufacturerDetailUiModel
import com.example.mermelalogistic.databinding.FragmentManufacturerWarehouseDetailBinding
import com.example.mermelalogistic.ext.observe
import com.example.mermelalogistic.ext.toast
import com.example.mermelalogistic.ui.feature.home.customer.adapter.ManufacturerDetailItemAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ManufacturerWarehouseDetailFragment : BaseFragment<FragmentManufacturerWarehouseDetailBinding,ManufacturerWarehouseDetailViewModel>(
    FragmentManufacturerWarehouseDetailBinding::inflate
) {
    override val viewModel: ManufacturerWarehouseDetailViewModel by viewModels()

    private val args by navArgs<ManufacturerWarehouseDetailFragmentArgs>()

    private var addProductDialog : BottomSheetDialog? = null

    override fun onCreateFinished() {
        viewModel.getFactoryInfo(args.factoryId)
        viewModel.getWarehouseWithStockInfo(args.factoryId)
    }

    override fun initListeners() {
    }

    override fun observeEvents() {
        lifecycleScope.launch {
            observe(viewModel.warehouseInfo){
                if(it.isNotEmpty()){
                    setManufacturerDetailRecyclerViewAdapter(it)
                }
            }
            observe(viewModel.factoryInfo){
                it?.let { factoryInfo ->
                    binding.toolbar.tvToolbarTitle.text = "${factoryInfo.name} Depo Bilgisi"
                }
            }
        }
    }

    private fun setManufacturerDetailRecyclerViewAdapter(warehouseInfo : List<ManufacturerDetailUiModel>){
        val mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvWarehouseItem.layoutManager = mLayoutManager

        binding.rvWarehouseItem.adapter =
            ManufacturerDetailItemAdapter(requireContext(),warehouseInfo,::onProductRequestClick)
    }

    private fun onProductRequestClick(manufacturerDetailUiModel : ManufacturerDetailUiModel){
        val view : View = layoutInflater.inflate(R.layout.dialog_request_product,null)
        addProductDialog = BottomSheetDialog(requireContext())
        addProductDialog!!.setContentView(view)
        addProductDialog!!.show()

        view.findViewById<TextInputEditText>(R.id.etProductName).setText(manufacturerDetailUiModel.productName)
        view.findViewById<TextInputEditText>(R.id.etInputManufacturerWarehouseId).setText(manufacturerDetailUiModel.warehouseId.toString())

        val warehouseId = view.findViewById<TextInputEditText>(R.id.etWarehouseId)
        val productAmount = view.findViewById<TextInputEditText>(R.id.etProductAmount)
        val btnRequestProduct = view.findViewById<Button>(R.id.btnRequestProduct)

        btnRequestProduct.setOnClickListener {
            kotlin.runCatching {
                val requestProductRequestModel = RequestProductRequestModel(
                    factoryId = manufacturerDetailUiModel.factoryId,
                    factoryWarehouseId = manufacturerDetailUiModel.warehouseId,
                    customerWarehouseId = warehouseId.text.toString().toLong(),
                    productId = manufacturerDetailUiModel.productId,
                    productAmount = productAmount.text.toString().toInt(),
                    productName = manufacturerDetailUiModel.productName
                )

                if(calculateProductAmountDiff(productAmount.text.toString().toInt(),manufacturerDetailUiModel.productQty)){
                    viewModel.fetchProductFromWarehouse(requestProductRequestModel)
                    addProductDialog?.dismiss()
                }else{
                    viewModel.requestProduct(requestProductRequestModel)
                    addProductDialog?.dismiss()
                }
            }.onFailure {
                toast("Lütfen girilen bilgileri kontrol ediniz.")
            }
        }
    }

    private fun calculateProductAmountDiff(inputAmount : Int,warehouseProductAmount : Int) : Boolean{
        return inputAmount <= warehouseProductAmount
    }
}