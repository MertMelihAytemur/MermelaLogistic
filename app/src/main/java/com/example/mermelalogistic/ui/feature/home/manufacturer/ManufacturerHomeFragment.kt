package com.example.mermelalogistic.ui.feature.home.manufacturer


import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.data.local.home.customer.CustomerWarehouseUiModel
import com.example.mermelalogistic.data.local.home.customer.RequestProductRequestModel
import com.example.mermelalogistic.data.local.home.manufacturer.AddProductRequestModel
import com.example.mermelalogistic.data.local.home.manufacturer.ProductRequestUiModel
import com.example.mermelalogistic.databinding.FragmentManufacturerHomeBinding
import com.example.mermelalogistic.ext.observe
import com.example.mermelalogistic.ext.toast
import com.example.mermelalogistic.ui.feature.home.customer.adapter.CustomerWarehouseItemAdapter
import com.example.mermelalogistic.ui.feature.home.manufacturer.adapter.ProductRequestListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ManufacturerHomeFragment :
    BaseFragment<FragmentManufacturerHomeBinding, ManufacturerHomeViewModel>(
        FragmentManufacturerHomeBinding::inflate
    ) {
    override val viewModel: ManufacturerHomeViewModel by viewModels()

    private var addProductDialog: BottomSheetDialog? = null

    private var readRequestDialog: BottomSheetDialog? = null

    private var requestList: List<ProductRequestUiModel> = emptyList()

    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.btnAddProduct.setOnClickListener {
            openAddProductBottomSheet()
        }
        binding.btnReadRequest.setOnClickListener {
            readCustomerRequest(requestList)
        }
    }

    private fun openAddProductBottomSheet() {
        val view: View = layoutInflater.inflate(R.layout.dialog_add_product, null)
        addProductDialog = BottomSheetDialog(requireContext())
        addProductDialog!!.setContentView(view)
        addProductDialog!!.show()

        val productName = view.findViewById<TextInputEditText>(R.id.etProductName)
        val warehouseId = view.findViewById<TextInputEditText>(R.id.etWarehouseId)
        val productAmount = view.findViewById<TextInputEditText>(R.id.etProductAmount)
        val btnAdd = view.findViewById<Button>(R.id.btnAddProduct)

        btnAdd.setOnClickListener {
            kotlin.runCatching {
                val addProductRequestModel = AddProductRequestModel(
                    productName = productName.text.toString(),
                    warehouseId = warehouseId.text.toString().toLong(),
                    productAmount = productAmount.text.toString().toInt()
                )
                Timber.i("REQUEST MODEL $addProductRequestModel")
                viewModel.addProduct(addProductRequestModel)
            }.onFailure {
                toast("Lütfen girilen bilgileri kontrol ediniz.")
            }

        }
    }

    override fun observeEvents() {
        lifecycleScope.launch {
            observe(viewModel.warehouseInfo) {
                if (it.isNotEmpty()) {
                    setWarehouseRecyclerViewAdapter(it)
                }
            }
            observe(viewModel.addProductResult) {
                if (it) {
                    addProductDialog?.let { dialog ->
                        dialog.dismiss()
                    }
                }
            }

            observe(viewModel.productRequestList){
                requestList = it
                readCustomerRequest(it)
            }

            observe(viewModel.factoryInfo) {
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
            CustomerWarehouseItemAdapter(requireContext(), warehouseInfo)
    }

    private fun readCustomerRequest(requestList: List<ProductRequestUiModel>) {
        readRequestDialog?.let {
            if(it.isShowing){
                it.dismiss()
            }
        }

        val view: View = layoutInflater.inflate(R.layout.dialog_view_request, null)
        readRequestDialog = BottomSheetDialog(requireContext())
        readRequestDialog!!.setContentView(view)
        readRequestDialog!!.show()

        val rvRequests = view.findViewById<RecyclerView>(R.id.rvProductRequest)
        val notFoundText = view.findViewById<TextView>(R.id.tvNotFound)

        if (requestList.isNotEmpty()) {
            rvRequests.visibility = View.VISIBLE
            notFoundText.visibility = View.GONE

        } else {
            rvRequests.visibility = View.GONE
            notFoundText.visibility = View.VISIBLE
        }

        val mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvRequests.layoutManager = mLayoutManager

        rvRequests.adapter =
            ProductRequestListAdapter(requireContext(), requestList, ::onRequestClickAction)

    }

    private fun onRequestClickAction(productRequestUiModel: ProductRequestUiModel) {
        readRequestDialog!!.dismiss()


        viewModel.navigateToOrderConfirm()
        /*val view: View = layoutInflater.inflate(R.layout.dialog_add_product, null)
        addProductDialog = BottomSheetDialog(requireContext())
        addProductDialog!!.setContentView(view)
        addProductDialog!!.show()

        view.findViewById<TextInputEditText>(R.id.etProductName)
            .setText(productRequestUiModel.productName)
        view.findViewById<TextInputEditText>(R.id.etProductAmount)
            .setText(productRequestUiModel.productAmount.toString())

        val warehouseId = view.findViewById<TextInputEditText>(R.id.etWarehouseId)
        val productName = view.findViewById<TextInputEditText>(R.id.etProductName)
        val productAmount = view.findViewById<TextInputEditText>(R.id.etProductAmount)
        val btnAdd = view.findViewById<Button>(R.id.btnAddProduct)

        btnAdd.setOnClickListener {
            kotlin.runCatching {
                val addProductRequestModel = AddProductRequestModel(
                    productName = productName.text.toString(),
                    warehouseId = warehouseId.text.toString().toLong(),
                    productAmount = productAmount.text.toString().toInt()
                )
                viewModel.addProduct(addProductRequestModel)
            }.onFailure {
                toast("Lütfen girilen bilgileri kontrol ediniz.")
            }
        }*/
    }
}