package com.example.estore.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estore.remote.data.Product
import com.example.estore.remote.repository.product.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun getProducts() = viewModelScope.launch {
        try {
            _products.value = repository.getProducts()
        } catch (e: Exception) {
            // Handle some error
        }

    }
}