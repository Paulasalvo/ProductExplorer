package com.desafio.productexplorer.view.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.desafio.productexplorer.databinding.ItemCategoryBinding
import com.desafio.productexplorer.model.data.Category

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var onClickItem:(Category)->Unit={}
    private var listCategory:List<Category> = mutableListOf()
    class CategoryViewHolder(val binding: ItemCategoryBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       val binding=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category= listCategory[position]
        holder.binding.tvNameCategory.text=category.name
        Glide
            .with(holder.binding.root.context)
            .load(category.imageURL)
            .into(holder.binding.ivCategory)
        holder.binding.llCategory.setOnClickListener{
            onClickItem(category)
        }
    }
    fun updateList (categories:List<Category>){
        listCategory=categories
        notifyDataSetChanged()
    }

}