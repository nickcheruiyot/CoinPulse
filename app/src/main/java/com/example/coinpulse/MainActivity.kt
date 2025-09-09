package com.example.coinpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.coinpulse.navigation.AppNavGraph
import com.example.coinpulse.navigation.BottomNavItem
import com.example.coinpulse.ui.theme.CoinPulseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinPulseTheme {
                val navController = rememberNavController()
                var selectedRoute by remember { mutableStateOf(BottomNavItem.Home.route) }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val items = listOf(
                                BottomNavItem.Home,
                                BottomNavItem.Favorites,
                                BottomNavItem.Profile
                            )
                            items.forEach { item ->
                                NavigationBarItem(
                                    selected = selectedRoute == item.route,
                                    onClick = {
                                        selectedRoute = item.route
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = { Icon(item.icon, contentDescription = item.label) },
                                    label = { Text(item.label) }
                                )
                            }
                        }
                    }
                ) { padding ->
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}
