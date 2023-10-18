package com.desafio.productexplorer.model.api.dto

data class ProductDTO(
    val category: CategoryDTO,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)
