package com.desafio.productexplorer.view.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.desafio.productexplorer.model.repository.PlatziFakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: PlatziFakeRepository
): ViewModel() {
    fun productsLiveData(id: Int)= repository.getProducts(id).asLiveData()

}