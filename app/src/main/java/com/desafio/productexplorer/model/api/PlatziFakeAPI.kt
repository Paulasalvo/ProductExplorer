package com.desafio.productexplorer.model.api

import com.desafio.productexplorer.model.api.dto.CategoryDTO
import com.desafio.productexplorer.model.api.dto.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PlatziFakeAPI {
    @GET("categories")
    suspend fun getCategories():List<CategoryDTO>

    @GET("categories/{id}/products")
    suspend fun getProducts(@Path("id") id:Int):List<ProductDTO>
}