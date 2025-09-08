
package com.example.coinpulse.ui.theme.coins

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
                contentPadding = padding
            ) {
                items(coins) { coin ->
                    CoinListItem(coin)
                }
            }
        }
    }
}

@Composable
fun CoinListItem(coin: Coin) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = coin.iconUrl,
            contentDescription = coin.name,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = coin.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Price: ${coin.price.take(8)} USD")
            Text(text = "24h: ${coin.change}%")
        }
    }
}
