package com.example.estore.utils.modules

import com.example.estore.remote.repository.product.ProductRepository
import com.example.estore.remote.repository.product.ProductRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}