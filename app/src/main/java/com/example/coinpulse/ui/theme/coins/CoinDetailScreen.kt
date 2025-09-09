package com.example.coinpulse.ui.theme.coins

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coinpulse.data.remote.Coin

@Composable
fun CoinDetailScreen(coin: Coin) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = coin.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Price: ${coin.price}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Change: ${coin.change}%")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "UUID: ${coin.uuid}")
    }
}
