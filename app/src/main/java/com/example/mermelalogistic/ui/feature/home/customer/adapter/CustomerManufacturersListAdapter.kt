package com.example.mermelalogistic.ui.feature.home.customer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mermelalogistic.data.local.home.customer.CustomerManufacturersUiModel
import com.example.mermelalogistic.databinding.RowManufacturerFactoriesItemBinding
import dagger.hilt.android.AndroidEntryPoint

class CustomerManufacturersListAdapter(
    private val context : Context,
    private val infoList : List<CustomerManufacturersUiModel>,
    private val onDetailButtonClick : (factoryId : String) -> Unit
) : RecyclerView.Adapter<CustomerManufacturersListAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val  binding : RowManufacturerFactoriesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val itemPosition = infoList[adapterPosition]

            binding.tvFactoryName.text = itemPosition.manufacturerName
            binding.tvFactoryAddress.text = itemPosition.manufacturerAddress
            binding.tvFactoryWarehouseCount.text = itemPosition.warehouseCount

            binding.cardview.setOnClickListener {
                onDetailButtonClick(itemPosition.manufacturerId)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerManufacturersListAdapter.MyViewHolder {
        val itemBinding =
            RowManufacturerFactoriesItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = infoList.size

}