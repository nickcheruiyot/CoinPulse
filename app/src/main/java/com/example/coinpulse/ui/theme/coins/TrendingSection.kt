package com.example.coinpulse.ui.theme.coins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coinpulse.data.remote.Coin

@Composable
fun TrendingSection(trendingCoins: List<Coin>) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Trending Coins 🔥", style = MaterialTheme.typography.titleMedium)
            Text(
                "All categories",
                color = Color.Green,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.height(8.dp))

        LazyRow {
            items(trendingCoins) { coin ->
                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(140.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = coin.iconUrl,
                            contentDescription = coin.name,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(coin.name, style = MaterialTheme.typography.bodyMedium)
                        Text("\$${coin.price.take(8)}", style = MaterialTheme.typography.bodySmall)
                        Text(
                            "${coin.change}%",
                            color = if (coin.change.startsWith("-")) Color.Red else Color.Green,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
