package com.example.estore.utils.modules

import com.example.estore.ui.products.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { ProductListViewModel(get()) }
}