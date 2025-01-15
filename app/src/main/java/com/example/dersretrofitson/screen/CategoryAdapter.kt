package com.example.dersretrofitson.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dersretrofitson.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = arrayListOf<String>()
    lateinit var onClickItem: (String) -> Unit

    inner class CategoryViewHolder(val itemCategoryBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemCategoryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val title = categoryList[position]

        holder.itemCategoryBinding.textView.text = title

        holder.itemCategoryBinding.textView.setOnClickListener {
            onClickItem(title)
        }
    }


    fun updateList(newList: List<String>) {
        categoryList.clear()
        categoryList.addAll(newList)
        notifyDataSetChanged()
    }

}