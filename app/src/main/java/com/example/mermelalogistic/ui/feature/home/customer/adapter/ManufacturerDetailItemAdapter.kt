package com.example.mermelalogistic.ui.feature.home.customer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mermelalogistic.data.local.home.manufacturer.ManufacturerDetailUiModel
import com.example.mermelalogistic.databinding.RowManufacturerDetailWarehouseItemBinding

class ManufacturerDetailItemAdapter(
    private val context : Context,
    private val infoList : List<ManufacturerDetailUiModel>,
    private val onProductClick : (manufacturerDetailUiModel : ManufacturerDetailUiModel) -> Unit
): RecyclerView.Adapter<ManufacturerDetailItemAdapter.MyViewHolder>() {
    inner class MyViewHolder(private val binding : RowManufacturerDetailWarehouseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            val itemPosition = infoList[adapterPosition]

            binding.tvWarehouseId.text = itemPosition.warehouseId.toString()
            binding.tvWarehouseName.text = itemPosition.warehouseName
            binding.tvProductName.text = itemPosition.productName
            binding.tvProductQty.text = itemPosition.productQty.toString()

            binding.cardView.setOnClickListener {
                onProductClick(itemPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ManufacturerDetailItemAdapter.MyViewHolder {
        val binding = RowManufacturerDetailWarehouseItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ManufacturerDetailItemAdapter.MyViewHolder,
        position: Int
    ) {
        holder.bind()
    }

    override fun getItemCount(): Int = infoList.size
}