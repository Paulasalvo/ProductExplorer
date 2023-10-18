package com.desafio.productexplorer.view.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafio.productexplorer.R
import com.desafio.productexplorer.databinding.ItemProductsBinding
import com.desafio.productexplorer.model.data.Product
import com.desafio.productexplorer.util.UtilFormat

class ProductsAdapter: RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    var onClickItem:(Product)->Unit={}
    private var listProducts:List<Product> = mutableListOf()
    class ProductsViewHolder(val binding: ItemProductsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding= ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product= listProducts[position]
        holder.binding.tvTitleProduct.text=product.title
        holder.binding.tvPriceProduct.text="$"+ product.price.toString()
        Glide
            .with(holder.binding.root.context)
            .load(product.images.getOrNull(0))
            .error(R.drawable.baseline_cancel_24)
            .into(holder.binding.ivProduct1)
        Glide
            .with(holder.binding.root.context)
            .load(product.images.getOrNull(1))
            .into(holder.binding.ivProduct2)
        Glide
            .with(holder.binding.root.context)
            .load(product.images.getOrNull(2))
            .into(holder.binding.ivProduct3)

        holder.binding.tvCreationAt.text= UtilFormat.getDateFormatted(product.creationAt)

        holder.binding.llProductItem.setOnClickListener(){
           onClickItem(product)
        }

    }
    fun updateList (products:List<Product>){
        listProducts=products
        notifyDataSetChanged()
    }


}