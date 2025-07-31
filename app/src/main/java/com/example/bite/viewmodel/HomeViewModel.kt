import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bite.api.ApiRoutes
import com.example.bite.api.RetrofitInstance
import com.example.bite.api.ApiService
import com.example.bite.models.Categoria
import com.example.bite.models.Product
import Restaurante
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val tokenProvider: () -> String?) : ViewModel() {

    private val _categories = MutableStateFlow<List<Categoria>>(emptyList())
    val categories: StateFlow<List<Categoria>> = _categories

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _restaurants = MutableStateFlow<List<Restaurante>>(emptyList())
    val restaurants: StateFlow<List<Restaurante>> = _restaurants

    private val _productsByRestaurant = MutableStateFlow<List<Product>>(emptyList())
    val productsByRestaurant: StateFlow<List<Product>> = _productsByRestaurant

    private val _selectedRestaurant = MutableStateFlow<Restaurante?>(null)
    val selectedRestaurant: StateFlow<Restaurante?> = _selectedRestaurant

    private val _restaurantsByCategory = MutableStateFlow<List<Restaurante>>(emptyList())
    val restaurantsByCategory: StateFlow<List<Restaurante>> = _restaurantsByCategory

    init {
        fetchCategoriesAndProductsAndRestaurants()
    }

    private fun fetchCategoriesAndProductsAndRestaurants() {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                val apiService = retrofit.create(ApiService::class.java)

                _categories.value = apiService.getCategories()
                _products.value = apiService.getProducts()
                _restaurants.value = apiService.getRestaurants()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Método para obtener un restaurante por ID
    fun fetchRestaurantById(restaurantId: Int) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                val apiService = retrofit.create(ApiService::class.java)
                val restaurant = apiService.getRestaurantById(restaurantId)
                _selectedRestaurant.value = restaurant
                fetchProductsByRestaurant(restaurantId) // Llama a este método para actualizar los productos
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Método para filtrar productos por restaurantId
    private fun fetchProductsByRestaurant(restaurantId: Int) {
        viewModelScope.launch {
            _productsByRestaurant.value = _products.value.filter { it.restaurantId == restaurantId }
        }
    }


    // Método para filtrar restaurantes por nombre de categoría
    fun fetchRestaurantsByCategory(categoryName: String) {
        viewModelScope.launch {
            _restaurantsByCategory.value = _restaurants.value.filter { it.categoria == categoryName }
        }
    }
}

