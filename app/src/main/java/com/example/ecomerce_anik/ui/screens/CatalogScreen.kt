package com.example.ecomerce_anik.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecomerce_anik.ui.navigation.Screen
import com.example.ecomerce_anik.ui.viewmodel.MainViewModel

import androidx.compose.foundation.lazy.grid.GridItemSpan

@Composable
fun CatalogScreen(navController: NavController, viewModel: MainViewModel) {
    val products by viewModel.filteredProducts.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Search Bar
        item(span = { GridItemSpan(2) }) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search products...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        }

        // Header and Sort
        item(span = { GridItemSpan(2) }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var showMenu by remember { mutableStateOf(false) }
                Text(
                    text = "All Products",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Box {
                    TextButton(onClick = { showMenu = true }) {
                        Text("Sort")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(text = { Text("Name A-Z") }, onClick = { viewModel.onSortOrderChange(MainViewModel.SortOrder.NAME_ASC); showMenu = false })
                        DropdownMenuItem(text = { Text("Name Z-A") }, onClick = { viewModel.onSortOrderChange(MainViewModel.SortOrder.NAME_DESC); showMenu = false })
                        DropdownMenuItem(text = { Text("Price Low-High") }, onClick = { viewModel.onSortOrderChange(MainViewModel.SortOrder.PRICE_ASC); showMenu = false })
                        DropdownMenuItem(text = { Text("Price High-Low") }, onClick = { viewModel.onSortOrderChange(MainViewModel.SortOrder.PRICE_DESC); showMenu = false })
                        DropdownMenuItem(text = { Text("Newest") }, onClick = { viewModel.onSortOrderChange(MainViewModel.SortOrder.ID_DESC); showMenu = false })
                        DropdownMenuItem(text = { Text("Oldest") }, onClick = { viewModel.onSortOrderChange(MainViewModel.SortOrder.ID_ASC); showMenu = false })
                    }
                }
            }
        }

        if (isLoading) {
            item(span = { GridItemSpan(2) }) {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        } else {
            items(products) { product ->
                ProductItem(
                    product = product,
                    onProductClick = {
                        navController.navigate(Screen.ProductDetails.passId(product.id))
                    },
                    onAddClick = {
                        viewModel.addToCart(product)
                    }
                )
            }
        }
    }
}
