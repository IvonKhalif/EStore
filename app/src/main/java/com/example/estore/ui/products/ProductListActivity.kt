package com.example.estore.ui.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.estore.ui.Greeting
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListActivity : ComponentActivity() {
    private val viewModel: ProductListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProducts()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "ProductListScreen"
            ) {
                composable(route = "ProductListScreen") {
                    ProductListScreen(navController)
                }
                composable(route = "DetailScreen") {
                    Greeting("This is Detail Page")
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProductListScreen(navController: NavController) {
        MaterialTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("My Products", fontWeight = FontWeight.Bold)
                            },
                            actions = {
                                IconButton(onClick = { /* Handle search action */ }) {
                                    Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                                }
                            },
                            modifier = Modifier.background(Color(0xFF6200EE))
                        )
                    },
                    content = { innerPadding ->
                        ProductListCard(viewModel, modifier = Modifier.padding(innerPadding), navController)
                    }
                )
            }
        }
    }


    @Composable
    fun ProductListCard(
        viewModel: ProductListViewModel = getViewModel(),
        modifier: Modifier,
        navController: NavController
    ) {
        val products by viewModel.products.observeAsState(emptyList())

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(route = "DetailScreen")
                        },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardColors(
                        containerColor = Color.White,
                        contentColor = Color.Transparent,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.LightGray
                    )
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = product.title.orEmpty(),
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(text = "$${product.price}", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}