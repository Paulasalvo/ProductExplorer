package com.desafio.productexplorer.view.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.desafio.productexplorer.model.repository.PlatziFakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    repository: PlatziFakeRepository
): ViewModel() {
    val categoryLiveData= repository.getCategory().asLiveData()

}