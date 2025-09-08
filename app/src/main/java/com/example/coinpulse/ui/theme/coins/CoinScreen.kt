package com.example.coinpulse.ui.theme.coins

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coinpulse.data.remote.Coin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsScreen(viewModel: CoinsViewModel = viewModel()) {
    val coins by viewModel.coins.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Top Coins") }
            )
        }
    ) { padding ->
        if (coins.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = padding
            ) {
                items(coins) { coin ->
                    CoinListItem(coin)
                }
            }
        }
    }
}
