package com.desafio.productexplorer.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val creationAt: String,
    val images: List<String>
):Parcelable