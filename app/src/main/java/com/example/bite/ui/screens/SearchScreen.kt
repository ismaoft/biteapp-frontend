import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bite.ui.components.InputField
import com.example.bite.ui.components.RestaurantRow
import com.example.bite.ui.components.CategoryRow

@Composable
fun SearchScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel // Pasado desde `UserNavGraph`
) {
    var searchQuery by remember { mutableStateOf("") }

    // Estados para las categorías, productos y restaurantes
    val categories by homeViewModel.categories.collectAsState()
    val products by homeViewModel.products.collectAsState()
    val restaurants by homeViewModel.restaurants.collectAsState()

    // Filtrar categorías, productos y restaurantes según la consulta de búsqueda
    val filteredCategories = if (searchQuery.isNotBlank()) {
        categories.filter { it.nombre.contains(searchQuery, ignoreCase = true) }
    } else {
        emptyList()
    }
    val filteredProducts = if (searchQuery.isNotBlank()) {
        products.filter { it.nombre.contains(searchQuery, ignoreCase = true) }
    } else {
        emptyList()
    }

    val filteredRestaurants = if (searchQuery.isNotBlank()) {
        restaurants.filter { it.nombre.contains(searchQuery, ignoreCase = true) }
    } else {
        emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF4BC9C4), Color(0xFFF5F5DC)),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
            .padding(16.dp)
    ) {
        // Barra de búsqueda
        InputField(
            value = searchQuery,
            onValueChange = { searchQuery = it }, // Actualizar la consulta de búsqueda
            label = "Search Bite"
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            // Mostrar resultados de búsqueda en Categorías
            if (filteredCategories.isNotEmpty()) {
                items(filteredCategories) { category ->
                    CategoryRow(
                        category = category,
                        onClick = { categoryName ->
                            navController.navigate("category_detail/$categoryName")
                        }
                    )
                }
            }

            // Mostrar resultados de búsqueda en Productos
           /** if (filteredProducts.isNotEmpty()) {
                items(filteredProducts) { product ->
                    ProductRow(product = product)
                }
            }**/

            // Mostrar resultados de búsqueda en Restaurantes
            if (filteredRestaurants.isNotEmpty()) {
                items(filteredRestaurants) { restaurant ->
                    RestaurantRow(
                        restaurant = restaurant,
                        onClick = { restaurantId ->
                            navController.navigate("restaurant_detail/$restaurantId")
                        }
                    )
                }
            }
        }
    }
}

