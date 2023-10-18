package com.desafio.productexplorer.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "product_detail_table",)
data class ProductDetailEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val creationAt: String,
    val rating: Int,
    val comment: String
)

