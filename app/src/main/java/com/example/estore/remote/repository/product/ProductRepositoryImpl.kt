package com.example.estore.remote.repository.product

import com.example.estore.remote.data.Product
import com.example.estore.services.ProductService

class ProductRepositoryImpl(private val service: ProductService) : ProductRepository {
    override suspend fun getProducts(): List<Product> = service.getProducts()
}