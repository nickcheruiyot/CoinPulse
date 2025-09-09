package com.example.coinpulse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.coinpulse.ui.theme.coins.CoinsScreen
import com.example.coinpulse.ui.theme.coins.CoinDetailScreen
import androidx.compose.material3.Text

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            CoinsScreen(onCoinClick = { coin ->
                navController.navigate("coinDetail/${coin.uuid}/${coin.name}/${coin.price}/${coin.change}")
            })
        }
        composable(BottomNavItem.Favorites.route) {
            Text("Favorites Screen")
        }
        composable(BottomNavItem.Profile.route) {
            Text("Profile Screen")
        }

        // Coin Detail
        composable(
            route = "coinDetail/{uuid}/{name}/{price}/{change}",
            arguments = listOf(
                navArgument("uuid") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("change") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val uuid = backStackEntry.arguments?.getString("uuid") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            val change = backStackEntry.arguments?.getString("change") ?: ""

            // For now, we create a simple Coin object just for display
            val coin = com.example.coinpulse.data.remote.Coin(
                uuid = uuid,
                name = name,
                iconUrl = "",
                price = price,
                change = change
            )

            CoinDetailScreen(coin)
        }
    }
}
