package com.example.coinpulse.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem("coins", Icons.Default.Home, "Home")
    object Favorites : BottomNavItem("favorites", Icons.Default.Star, "Favorites")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}