package com.desafio.productexplorer.model.repository

import com.desafio.productexplorer.model.api.dto.ProductDTO
import com.desafio.productexplorer.model.data.Category
import com.desafio.productexplorer.model.data.Product
import com.desafio.productexplorer.model.data.ProductDetail
import kotlinx.coroutines.flow.Flow

interface PlatziFakeRepository {
    fun getCategory():Flow<List<Category>>

    fun getProducts(id:Int):Flow<List<Product>>

    fun getProductDetail(id: Int) : Flow<ProductDetail>

    suspend fun setProductDetail(productDetail: ProductDetail)
}