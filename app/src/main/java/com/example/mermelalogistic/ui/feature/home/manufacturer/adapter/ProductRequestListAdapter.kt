package com.example.mermelalogistic.ui.feature.home.manufacturer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mermelalogistic.data.local.home.manufacturer.ProductRequestUiModel
import com.example.mermelalogistic.databinding.RowProductRequestItemBinding

class ProductRequestListAdapter(
    private val context: Context,
    private val requestList: List<ProductRequestUiModel>,
    private val onRequestClick : (ProductRequestUiModel) ->Unit
) : RecyclerView.Adapter<ProductRequestListAdapter.MyViewHolder>() {
    inner class MyViewHolder(private val binding: RowProductRequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val itemPosition = requestList[adapterPosition]

            binding.tvCustomerWarehouseName.text = itemPosition.customerWarehouseName
            binding.tvProductName.text = itemPosition.productName
            binding.tvProductAmount.text = itemPosition.productAmount.toString()
            binding.tvDate.text = itemPosition.requestDate.toString()

            binding.cardView.setOnClickListener {
                onRequestClick(itemPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductRequestListAdapter.MyViewHolder {
        val binding =
            RowProductRequestItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductRequestListAdapter.MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = requestList.size
}