package com.desafio.productexplorer.model.repository

import com.desafio.productexplorer.model.api.PlatziFakeAPI
import com.desafio.productexplorer.model.data.Category
import com.desafio.productexplorer.model.data.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlatziFakeRepositoryImpl @Inject constructor(
    private val apiService: PlatziFakeAPI
): PlatziFakeRepository {
    override fun getCategory(): Flow<List<Category>> {
       return flow {
           val response = apiService.getCategories()
           emit(response.map { Category(it.id, it.name, it.image) })
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
}