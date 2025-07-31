package com.example.bite.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object AdminHome : Screen("adminHome")
    object Register1 : Screen("register1")
    object Register2 : Screen("register2")
    object Register3 : Screen("register3")
    object Loading : Screen("loading")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object Cart : Screen("cart")
    object Notifications : Screen("notifications")
    object Dashboard : Screen("admin_dashboard")
    object AdminOrders : Screen("admin_orders")
    object AdminSettings : Screen("admin_settings")
    object OrderConfirmation : Screen("order_confirmation")
    object OrderTracking : Screen("OrderTracking")


    // Nuevas rutas para las pantallas de detalles
    data class CategoryDetail(val categoryName: String) : Screen("category_detail/$categoryName")
    data class RestaurantDetail(val restaurantId: Int) : Screen("restaurant_detail/$restaurantId")
}

