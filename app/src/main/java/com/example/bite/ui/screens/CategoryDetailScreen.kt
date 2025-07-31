import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bite.ui.components.RestaurantRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    navController: NavHostController,
    categoryName: String,
    homeViewModel: HomeViewModel
) {
    val restaurants by homeViewModel.restaurantsByCategory.collectAsState()

    LaunchedEffect(categoryName) {
        homeViewModel.fetchRestaurantsByCategory(categoryName)
    }

    Column {
        TopAppBar(
            title = { Text(text = categoryName) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(restaurants) { restaurant ->
                RestaurantRow(restaurant = restaurant, onClick = { restaurantId ->
                    navController.navigate("restaurant_detail/$restaurantId")
                })
            }
        }
    }
}
