package com.example.dersretrofitson.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dersretrofitson.databinding.ItemProductBinding
import com.example.dersretrofitson.model.ProductResponseModelItem
import com.example.dersretrofitson.util.goneItem
import com.example.dersretrofitson.util.visibleItem

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val productList = arrayListOf<ProductResponseModelItem>()

    inner class ProductViewHolder(val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )

        return ProductViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.itemProductBinding.product = product

        holder.itemProductBinding.root.setOnClickListener {
            /* Navigation.findNavController(it)
                 .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(product.id.toString()))*/
        }

        holder.itemProductBinding.textViewProductItemTitle.setOnClickListener {
            holder.itemProductBinding.textViewProductItemTitle.goneItem()
            holder.itemProductBinding.textViewTitleLong.visibleItem()
        }
    }

    fun updateList(newList: List<ProductResponseModelItem>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}