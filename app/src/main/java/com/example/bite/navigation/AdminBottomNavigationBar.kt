package com.example.bite.navigation

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AdminBottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color(0xFF00C3BE),
        contentColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.ShowChart, contentDescription = "Dashboard") },
            label = {
                Text(
                    "Dashboard",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            selected = true,
            onClick = { navController.navigate(Screen.Dashboard.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Orders") },
            label = {
                Text(
                    "Orders",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            selected = false,
            onClick = { navController.navigate(Screen.AdminOrders.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = {
                Text(
                    "Settings",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            selected = false,
            onClick = { navController.navigate(Screen.AdminSettings.route) }
        )
    }
}
