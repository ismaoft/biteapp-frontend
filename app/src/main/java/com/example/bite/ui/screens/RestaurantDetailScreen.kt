import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bite.ui.components.ProductRow
import com.example.bite.viewmodel.CartViewModel
import com.example.bite.viewmodel.CartViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailScreen(
    navController: NavHostController,
    restaurantId: Int,
    homeViewModel: HomeViewModel,
    tokenProvider: () -> String? // Agregar el tokenProvider como parámetro
) {
    // Crear la instancia del CartViewModel usando el CartViewModelFactory
    val cartViewModelFactory = CartViewModelFactory(tokenProvider)
    val cartViewModel: CartViewModel = viewModel(factory = cartViewModelFactory)

    val restaurant by homeViewModel.selectedRestaurant.collectAsState()
    val products by homeViewModel.productsByRestaurant.collectAsState()

    LaunchedEffect(restaurantId) {
        homeViewModel.fetchRestaurantById(restaurantId)
    }

    restaurant?.let { restaurantData ->
        Column {
            TopAppBar(
                title = { Text(text = restaurantData.nombre) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) { product ->
                    ProductRow(
                        product = product,
                        onAddToCartClick = {
                            // Usamos el ViewModel para agregar el producto al carrito
                            cartViewModel.addProductToCart(product, cantidad = 1)
                        }
                    )
                }
            }
        }
    }
}
