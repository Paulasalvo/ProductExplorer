package com.desafio.productexplorer.model.repository

import com.desafio.productexplorer.model.api.PlatziFakeAPI
import com.desafio.productexplorer.model.data.Category
import com.desafio.productexplorer.model.data.Product
import com.desafio.productexplorer.model.data.ProductDetail
import com.desafio.productexplorer.model.database.ProductDAO
import com.desafio.productexplorer.model.database.ProductDetailEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlatziFakeRepositoryImpl @Inject constructor(
    private val apiService: PlatziFakeAPI,
    private val productDAO: ProductDAO
): PlatziFakeRepository {
    override fun getCategory(): Flow<List<Category>> {
       return flow {
           try {
               val response = apiService.getCategories()
               emit(response.map {
                   Category(
                       it.id ?: 0,
                       it.name.orEmpty(),
                       it.image.orEmpty()
                   )
               })
           }catch (e: Exception){
               emit(emptyList())
           }

       }
    }

    override fun getProducts(id:Int): Flow<List<Product>> {
        return flow {
            val response = apiService.getProducts(id)
            emit(response.map {
                Product(
                    id = it.id,
                    title = it.title,
                    description= it.description,
                    price = it.price,
                    creationAt = it.creationAt,
                    images = it.images
                )
            })
        }
    }

    override suspend fun getProductDetail(id: Int): ProductDetail {
        val productDetailEntity = productDAO.getProductDetail(id)
        return ProductDetail(
            id = id,
            title = productDetailEntity?.title.orEmpty(),
            description = productDetailEntity?.description.orEmpty(),
            price = productDetailEntity?.price ?: 0,
            creationAt = productDetailEntity?.creationAt.orEmpty(),
            rating = productDetailEntity?.rating ?: 0,
            comment =productDetailEntity?.comment.orEmpty()
        )
    }

    override suspend fun setProductDetail(productDetail: ProductDetail) {
       productDAO.insertProductDetail(
           ProductDetailEntity(
               productDetail.id,
               productDetail.title,
               productDetail.description,
               productDetail.price,
               productDetail.creationAt,
               productDetail.rating,
               productDetail.comment
           )
       )
    }
}