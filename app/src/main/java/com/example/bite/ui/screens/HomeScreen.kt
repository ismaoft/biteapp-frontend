import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bite.ui.components.CategoryRow
import com.example.bite.ui.components.RestaurantRow
import com.example.bite.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

// Importar la extensión circularList desde el archivo Extensions.kt
import com.example.bite.utils.circularList

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    authViewModel: AuthViewModel
) {
    val categories by homeViewModel.categories.collectAsState()
    val restaurants by homeViewModel.restaurants.collectAsState()

    // Estado de desplazamiento para el carrusel de categorías
    val categoryListState = rememberLazyListState()

    // Agrupar los restaurantes por categoría
    val groupedRestaurants = restaurants.groupBy { it.categoria }

    // Recordar los estados de desplazamiento para cada carrusel
    val restaurantListStates = remember {
        mutableMapOf<String, LazyListState>()
    }

    // Estado de desplazamiento para la columna vertical
    val columnState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Título atractivo para categorías
        Text(
            text = "¿Qué se te antoja hoy?",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Movimiento automático para el carrusel de categorías
        LaunchedEffect(categoryListState) {
            while (true) {
                delay(3000)
                val itemCount = categoryListState.layoutInfo.totalItemsCount
                if (itemCount > 0) {
                    val currentIndex = categoryListState.firstVisibleItemIndex
                    val nextIndex = (currentIndex + 1) % itemCount
                    categoryListState.animateScrollToItem(nextIndex)
                }
            }
        }

        // Carrusel de categorías con efecto circular
        LazyRow(
            state = categoryListState,
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories.circularList()) { category ->
                CategoryRow(
                    category = category,
                    onClick = { categoryName ->
                        navController.navigate("category_detail/$categoryName")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Título atractivo para restaurantes destacados
        Text(
            text = "Restaurantes destacados cerca de ti",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Carruseles de restaurantes agrupados por categoría
        LazyColumn(
            state = columnState,
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            groupedRestaurants.forEach { (categoryName, restaurants) ->
                item {
                    Text(
                        text = categoryName,
                        style = MaterialTheme.typography.headlineSmall, // Cambia a un estilo más grande
                        fontSize = 20.sp, // O especifica manualmente el tamaño
                        fontWeight = FontWeight.Bold, // Hazlo más impactante si deseas
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                    )
                }

                item {
                    val restaurantListState = restaurantListStates.getOrPut(categoryName) {
                        rememberLazyListState()
                    }

                    // Desplazamiento automático para cada carrusel horizontal
                    LaunchedEffect(restaurantListState) {
                        var forward = true
                        while (true) {
                            delay(3000)
                            val itemCount = restaurantListState.layoutInfo.totalItemsCount
                            if (itemCount > 0) {
                                val currentIndex = restaurantListState.firstVisibleItemIndex
                                val nextIndex = if (forward) currentIndex + 1 else currentIndex - 1
                                if (nextIndex in 0 until itemCount) {
                                    restaurantListState.animateScrollToItem(nextIndex)
                                } else {
                                    forward = !forward
                                }
                            }
                        }
                    }

                    // Carrusel horizontal con efecto circular
                    LazyRow(
                        state = restaurantListState,
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        items(restaurants.circularList()) { restaurant ->
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

        // Desplazamiento automático vertical (bucle) para la columna
        LaunchedEffect(columnState) {
            while (true) {
                delay(3000)
                val itemCount = columnState.layoutInfo.totalItemsCount
                if (itemCount > 0) {
                    val nextIndex = (columnState.firstVisibleItemIndex + 1) % itemCount
                    columnState.animateScrollToItem(nextIndex)
                }
            }
        }
    }
}
