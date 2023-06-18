package com.example.mermelalogistic.ui.feature.home.customer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mermelalogistic.data.local.home.customer.CustomerWarehouseUiModel
import com.example.mermelalogistic.databinding.RowWarehouseItemBinding

class CustomerWarehouseItemAdapter(
    private val context : Context,
    private val infoList : List<CustomerWarehouseUiModel>
): RecyclerView.Adapter<CustomerWarehouseItemAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val  binding : RowWarehouseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val itemPosition = infoList[adapterPosition]

            binding.tvWarehouseId.text = itemPosition.warehouseId.toString()
            binding.tvWarehouseName.text = itemPosition.warehouseName
            binding.tvProductName.text = itemPosition.productName
            binding.tvProductQty.text = itemPosition.productQty.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerWarehouseItemAdapter.MyViewHolder {
        val itemBinding =
            RowWarehouseItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: CustomerWarehouseItemAdapter.MyViewHolder,
        position: Int
    ) {
        holder.bind()
    }

    override fun getItemCount(): Int = infoList.size
}