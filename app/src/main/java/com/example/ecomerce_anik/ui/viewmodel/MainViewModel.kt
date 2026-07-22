package com.example.ecomerce_anik.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomerce_anik.domain.model.CartItem
import com.example.ecomerce_anik.domain.model.Product
import com.example.ecomerce_anik.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _snackbarEvent = MutableSharedFlow<String>()
    val snackbarEvent = _snackbarEvent.asSharedFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _sortOrder = MutableStateFlow(SortOrder.NONE)
    val sortOrder = _sortOrder.asStateFlow()

    enum class SortOrder {
        NONE, NAME_ASC, NAME_DESC, PRICE_ASC, PRICE_DESC, ID_ASC, ID_DESC
    }

    fun onSortOrderChange(order: SortOrder) {
        _sortOrder.value = order
    }

    val cartItems = repository.getCartItems().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val wishlistItems = repository.getWishlistItems().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    init {
        loadProducts()
        loadCategories()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getProducts()
                .onSuccess {
                    _products.value = it
                }
                .onFailure {
                    _snackbarEvent.emit("Network error. Showing sample products.")
                }
            _isLoading.value = false
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            repository.getCategories().onSuccess {
                _categories.value = listOf("All") + it
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelect(category: String) {
        _selectedCategory.value = category
        if (category == "All") {
            loadProducts()
        } else {
            viewModelScope.launch {
                _isLoading.value = true
                repository.getProductsByCategory(category).onSuccess {
                    _products.value = it
                }
                _isLoading.value = false
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            repository.addToCart(product)
            _snackbarEvent.emit("✅ Added to cart")
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            repository.removeFromCart(cartItem)
            _snackbarEvent.emit("Removed from cart")
        }
    }

    fun updateCartQuantity(id: Int, quantity: Int) {
        viewModelScope.launch {
            if (quantity > 0) {
                repository.updateCartQuantity(id, quantity)
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

    fun toggleWishlist(product: Product) {
        viewModelScope.launch {
            repository.toggleWishlist(product)
            val isFav = repository.isFavourite(product.id)
            _snackbarEvent.emit(if (isFav) "❤️ Added to wishlist" else "💔 Removed from wishlist")
        }
    }

    val filteredProducts = combine(_products, _searchQuery, _sortOrder) { products, query, order ->
        val filtered = if (query.isBlank()) {
            products
        } else {
            products.filter { it.title.contains(query, ignoreCase = true) }
        }
        
        when (order) {
            SortOrder.NAME_ASC -> filtered.sortedBy { it.title }
            SortOrder.NAME_DESC -> filtered.sortedByDescending { it.title }
            SortOrder.PRICE_ASC -> filtered.sortedBy { it.price }
            SortOrder.PRICE_DESC -> filtered.sortedByDescending { it.price }
            SortOrder.ID_ASC -> filtered.sortedBy { it.id }
            SortOrder.ID_DESC -> filtered.sortedByDescending { it.id }
            SortOrder.NONE -> filtered
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
