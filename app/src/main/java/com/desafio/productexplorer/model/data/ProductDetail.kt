package com.desafio.productexplorer.model.data

data class ProductDetail(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val creationAt: String,
    val rating: Int,
    val comment: String
)
