package com.example.ecomerce_anik.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Catalog : Screen("catalog")
    object Cart : Screen("cart")
    object Wishlist : Screen("wishlist")
    object Profile : Screen("profile")
    object Checkout : Screen("checkout")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object OrderSuccess : Screen("order_success")
    object Admin : Screen("admin")
    object ProductDetails : Screen("details/{productId}") {
        fun passId(id: Int) = "details/$id"
    }
}
