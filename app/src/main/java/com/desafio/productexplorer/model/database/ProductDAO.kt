package com.desafio.productexplorer.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {
    @Insert
    fun insertProductDetail(productDetailEntity: ProductDetailEntity)

    @Query("SELECT * FROM product_detail_table WHERE id=:id")
    fun getProductDetail(id:Int): Flow<ProductDetailEntity>


}