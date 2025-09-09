package com.example.coinpulse.ui.theme.coins

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import com.example.coinpulse.data.remote.Coin

@Composable
fun CoinListItem(
    coin: Coin,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ Coin Logo (SVG support via Coil)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coin.iconUrl)
                    .decoderFactory(SvgDecoder.Factory()) // Needed for SVG icons
                    .crossfade(true)
                    .build(),
                contentDescription = "${coin.name} logo",
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // ✅ Coin Name + Symbol
            Column {
                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // ✅ Price + Change %
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${coin.price.take(8)}", // shorten long decimals
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${coin.change}%",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (coin.change.toDoubleOrNull() ?: 0.0 >= 0)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.error
                )
            }
        }
    }
}


