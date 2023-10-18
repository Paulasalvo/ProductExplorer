package com.desafio.productexplorer.view.productdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.desafio.productexplorer.model.data.ProductDetail
import com.desafio.productexplorer.model.repository.PlatziFakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: PlatziFakeRepository
): ViewModel() {

    var productDetailLiveData: MutableLiveData<ProductDetail> = MutableLiveData()
    fun productDetail(id: Int) {
        viewModelScope.launch {
            val productDetail = repository.getProductDetail(id)
            productDetailLiveData.postValue(productDetail)
        }

    }

    fun saveProductDetail(productDetail: ProductDetail){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setProductDetail(productDetail)
        }
    }

}